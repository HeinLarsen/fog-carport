package dat.backend.model.persistence;

import dat.backend.model.entities.Fitting;
import dat.backend.model.entities.OrderItem;
import dat.backend.model.entities.Wood;
import dat.backend.model.exceptions.DatabaseException;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderItemMapper {
    protected static ArrayList<OrderItem> getOrderItemsByOrderId(int id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select * from order_item where order_id = ?";
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    int ID = rs.getInt("id");
                    int quantity = rs.getInt("quantity");
                    int price = rs.getInt("price");
                    String description = rs.getString("description");
                    orderItems.add(new OrderItem(ID, quantity, price, description));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error getting all order items");
        }
        return orderItems;
    }

    protected static ArrayList<OrderItem> getOrderItemsWoodByOrderId(int id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select order_item_wood.id, order_item_wood.quantity, order_item_wood.price, order_item_wood.description, wood.name, wood.length, wood.width, wood.height, wood.is_pressure_treated, c.category, u.unit from order_item_wood join wood on order_item_wood.item_id = wood.id join category c on wood.category = c.id join unit u on wood.unit = u.id where order_id = ?";
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    int ID = rs.getInt("id");
                    int quantity = rs.getInt("quantity");
                    int price = rs.getInt("price");
                    String description = rs.getString("description");
                    String name = rs.getString("name");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    String category = rs.getString("category");
                    String unit = rs.getString("unit");
                    boolean isPressureTreated = rs.getBoolean("is_pressure_treated");
                    OrderItem orderItem = new OrderItem(ID, quantity, price, description);
                    Wood wood = new Wood(name, length, price, unit, category, width, height, isPressureTreated);
                    orderItem.setMaterial(wood);
                    orderItems.add(orderItem);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error getting all order items");
        }
        return orderItems;
    }

    protected static ArrayList<OrderItem> getOrderItemsFittingByOrderId(int id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select order_item_fitting.id, order_item_fitting.quantity, order_item_fitting.price, order_item_fitting.description, fitting.name, fitting.length, fitting.width, fitting.height, u.unit from order_item_fitting join fitting on order_item_fitting.item_id = fitting.id join unit u on fitting.unit = u.id where order_id = ?";
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    int ID = rs.getInt("id");
                    int quantity = rs.getInt("quantity");
                    int price = rs.getInt("price");
                    String description = rs.getString("description");
                    String name = rs.getString("name");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    String unit = rs.getString("unit");
                    OrderItem orderItem = new OrderItem(ID, quantity, price, description);
                    Fitting fitting = new Fitting(name, length, price, unit, width, height);
                    orderItem.setMaterial(fitting);
                    orderItems.add(orderItem);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error getting all order items");
        }
        return orderItems;

    protected static void createOrderItemWood(OrderItem orderItem, int orderId, int woodId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "insert into order_item_wood (quantity, price, order_id, description, item_id) values (?, ?, ?, ?, ?)";

        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderItem.getQuantity());
                ps.setInt(2, orderItem.getPrice());
                ps.setInt(3, orderId);
                ps.setString(4, orderItem.getDescription());
                ps.setInt(5, woodId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException(e, "Error creating order item");
        }
    }
}
