package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.Status;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;

public class OrderMapper {
    protected static ArrayList<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select * from `order`";
        ArrayList<Order> orders = new ArrayList<>();
        Order order = null;
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    int ID = rs.getInt("ID");
                    Timestamp timestamp = rs.getTimestamp("created");
                    Status status = Status.valueOf(rs.getString("status"));
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    boolean shed = rs.getBoolean("shed");
                    order = new Order(ID, timestamp, status, length, width, shed);
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error getting all orders");
        }
        return orders;
    }

    protected static Order getOrderById(int id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select * from `order` where ID = ?";
        Order order = null;
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    int ID = rs.getInt("ID");
                    Timestamp timestamp = rs.getTimestamp("created");
                    Status status = Status.valueOf(rs.getString("status"));
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    boolean shed = rs.getBoolean("shed");
                    order = new Order(ID, timestamp, status, length, width, shed);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException(e, "Error getting all orders");
        }
        return order;
    }


    protected static ArrayList<Order> getOrdersByUserId(int userId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select * from `order` where user_id = ?";
        ArrayList<Order> orders = new ArrayList<>();
        Order order = null;
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    int ID = rs.getInt("ID");
                    Timestamp timestamp = rs.getTimestamp("created");
                    Status status = Status.valueOf(rs.getString("status"));
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    boolean shed = rs.getBoolean("shed");
                    order = new Order(ID, timestamp, status, length, width, shed);
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException(e, "Error getting all user orders");

        }
        return orders;
    }



    protected static void approveOrder(Order order, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "update `order` set status = ? where ID = ?";
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, order.getStatus().toString());
                ps.setInt(2, order.getOrderID());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error approving order");
        }
    }

    protected static ArrayList<Order> getOrdersByStatus(Status status, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select * from `order` where status = ?";
        ArrayList<Order> orders = new ArrayList<>();
        Order order = null;
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, status.toString());
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    int ID = rs.getInt("ID");
                    Timestamp timestamp = rs.getTimestamp("created");
                    Status orderStatus = Status.valueOf(rs.getString("status"));
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    boolean shed = rs.getBoolean("shed");
                    order = new Order(ID, timestamp, orderStatus, length, width, shed);
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error getting all orders");
        }
        return orders;
    }

   protected static int createOrder(Order order, int userId, ConnectionPool connectionPool) throws DatabaseException{
        String sql = "insert into `order` (length, width, shed, shed_length, shed_width, user_id) values (?, ?, ?, ?, ?, ?)";
        int id = -1;
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, order.getLength());
                ps.setInt(2, order.getWidth());
                ps.setBoolean(3, order.isShed());
                ps.setInt(4, order.getShedLength());
                ps.setInt(5, order.getShedWidth());
                ps.setInt(6, userId);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected != 1) {
                    throw new DatabaseException("Error creating order");
                }
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        id = generatedKeys.getInt(1);
                    } else {
                        throw new DatabaseException("Failed to get ID for created order");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DatabaseException(ex, "Could not create user in database");
        }
       return id;
   }
}
