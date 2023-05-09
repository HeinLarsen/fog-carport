package dat.backend.model.persistence;

import dat.backend.model.entities.Material;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialMapper {
    protected static ArrayList<Material> getAllMaterials(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select * from material inner join type on material.type = type.id inner join packaging on material.packaging = packaging.id";
        ArrayList<Material> materials = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    String type = rs.getString("type");
                    String packaging = rs.getString("packaging");
                    int price = rs.getInt("price");
                    int amount = rs.getInt("amount");
                    Material material = new Material(type, packaging, price, amount);
                    materials.add(material);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error getting all materials");
        }
        return materials;
    }

    protected static Material getMaterialByType(String type, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select * from material inner join type on material.type = type.id inner join packaging on material.packaging = packaging.id where type = ?";
        Material material = null;
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, type);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    String packaging = rs.getString("packaging");
                    String name = rs.getString("name");
                    int width = rs.getInt("width");
                    int length = rs.getInt("length");
                    int height = rs.getInt("height");
                    double price = rs.getInt("price");
                    int diameter = rs.getInt("diameter");
                    int quantity = rs.getInt("quantity");
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error getting all materials");
        }
        return material;
    }
}
