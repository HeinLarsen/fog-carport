package dat.backend.model.persistence;

import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MaterialMapper {
    protected static ArrayList<AMaterial> getAllMaterials(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select material.*, type.type as name, packaging.type as packaging, material_type.material as material_type from material inner join type on material.type = type.id inner join packaging on material.packaging = packaging.id inner join material_type on material.material_type_id = material_type.id";
        ArrayList<AMaterial> materials = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    String packaging = rs.getString("packaging");
                    String description = rs.getString("description");
                    int width = rs.getInt("width");
                    int length = rs.getInt("length");
                    int height = rs.getInt("height");
                    double price = rs.getInt("price");
                    int diameter = rs.getInt("diameter");
                    int quantity = rs.getInt("quantity");
                    String materialType = rs.getString("material_type");
                    switch (materialType) {
                        case "wood":
                            materials.add(new Wood(name, length, price, packaging, description, width, height));
                            break;
                        case "screw":
                            materials.add(new Screw(name, length, price, packaging, description, diameter));
                            break;
                        case "roof tile":
                            materials.add(new RoofTile(name, length, price, packaging, description, width, height));
                            break;
                        case "fitting":
                            materials.add(new Fitting(name, length, price, packaging, description, width, height));
                            break;
                        case "screwPack":
                            materials.add(new ScrewPack(name, length, price, packaging, description, diameter, quantity));
                            break;
                        default:
                            throw new DatabaseException("Unknown material type");
                    }
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error getting all materials");
        }
        return materials;
    }

    protected static AMaterial getMaterialById(int id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select material.*, type.type as name, packaging.type as packaging, material_type.material as material_type from material \n" +
                "inner join type on material.type = type.id \n" +
                "inner join packaging on material.packaging = packaging.id \n" +
                "inner join material_type on material.material_type_id = material_type.id \n" +
                "where material.id = ?";
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    String name = rs.getString("name");
                    String packaging = rs.getString("packaging");
                    String description = rs.getString("description");
                    int width = rs.getInt("width");
                    int length = rs.getInt("length");
                    int height = rs.getInt("height");
                    double price = rs.getInt("price");
                    int diameter = rs.getInt("diameter");
                    int quantity = rs.getInt("quantity");
                    String materialType = rs.getString("material_type");
                    switch(materialType) {
                        case "wood":
                           return new Wood(name, length, price, packaging, description, width, height);
                        case "screw":
                            return new Screw(name, length, price, packaging, description, diameter);
                        case "roof tile":
                            return new RoofTile(name, length, price, packaging, description, width, height);
                        case "fitting":
                            return new Fitting(name, length, price, packaging, description, width, height);
                        case "screwPack":
                            return new ScrewPack(name, length, price, packaging, description, diameter, quantity);
                        default:
                            throw new DatabaseException("Unknown material type");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException(e, "Error getting a material");
        }

        return null;
    }
}
