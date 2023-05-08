package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderMapper {
    public static ArrayList<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select * from order";

        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                ArrayList<Order> orders = new ArrayList<>();
                while(rs.next()) {
                }
                return orders;
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error getting all orders");
        }

    }


}
