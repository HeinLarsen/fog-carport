package dat.backend.model.persistence;

import dat.backend.model.entities.OrderItem;
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
        OrderItem orderItem = null;
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    int ID = rs.getInt("ID");
                    int amount = rs.getInt("amount");
                    int price = rs.getInt("price");
                    int materialID = rs.getInt("material_id");
                    orderItem = new OrderItem(ID, amount, price, materialID);
                }
                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error getting all order items");
        }
        return orderItems;
    }

    protected static void createOrderItem(OrderItem orderItem, int orderId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "insert into order_item (amount, total_price, order_id) values (?, ?, ?)";

        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderItem.getAmount());
                ps.setInt(2, orderItem.getPrice());
                ps.setInt(3, orderId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error creating order item");
        }
    }
}
