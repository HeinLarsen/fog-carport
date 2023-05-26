package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.Status;
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

    protected static void createOrder(Order order, int userId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "insert into `order` (created, status, user_id) values (?, ?, ?)";
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setTimestamp(1, order.getCreated());
                ps.setString(2, order.getStatus().toString());
                ps.setInt(3, userId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error creating order");
        }
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

   protected static void insertorder(int id, int userId, double length, double width, boolean shed, ConnectionPool connectionPool) throws DatabaseException{
        String sql = "insert into `order` (id, length, width, shed, user_id) values (?, ?, ?, ?)";
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.setDouble(2, length);
                ps.setDouble(3, width);
                ps.setBoolean(4, shed);
                ps.setInt(5, userId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error creating order");
        }

   }
}
