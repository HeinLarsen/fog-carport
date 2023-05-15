package dat.backend.model.persistence;

import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MaterialMapper {
//    protected static ArrayList<AMaterial> getAllMaterials(ConnectionPool connectionPool) throws DatabaseException {
//        String sql = "select material.*, type.type as name, packaging.type as packaging, material_type.material as material_type from material inner join type on material.type = type.id inner join packaging on material.packaging = packaging.id inner join material_type on material.material_type_id = material_type.id";
//        ArrayList<AMaterial> materials = new ArrayList<>();
//        try (Connection connection = connectionPool.getConnection()) {
//            try (PreparedStatement ps = connection.prepareStatement(sql)) {
//                ResultSet rs = ps.executeQuery();
//                while (rs.next()) {
//                    String name = rs.getString("name");
//                    String packaging = rs.getString("packaging");
//                    String description = rs.getString("description");
//                    int width = rs.getInt("width");
//                    int length = rs.getInt("length");
//                    int height = rs.getInt("height");
//                    double price = rs.getInt("price");
//                    int diameter = rs.getInt("diameter");
//                    int quantity = rs.getInt("quantity");
//                    String materialType = rs.getString("material_type");
//                    switch (materialType) {
//                        case "wood":
//                            materials.add(new Wood(name, length, price, packaging, description, width, height));
//                            break;
//                        case "screw":
//                            materials.add(new Screw(name, length, price, packaging, description, diameter));
//                            break;
//                        case "roof tile":
//                            materials.add(new RoofTile(name, length, price, packaging, description, width, height));
//                            break;
//                        case "fitting":
//                            materials.add(new Fitting(name, length, price, packaging, description, width, height));
//                            break;
//                        case "screwPack":
//                            materials.add(new ScrewPack(name, length, price, packaging, description, diameter, quantity));
//                            break;
//                        default:
//                            throw new DatabaseException("Unknown material type");
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            throw new DatabaseException(e, "Error getting all materials");
//        }
//        return materials;
//    }

    protected static ArrayList<Wood> getAllWood(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select wood.name, wood.height, wood.width, wood.length, wood.is_pressure_treated, wood.price, c.category, u.unit from wood join category c on wood.category = c.id join unit u on wood.unit = u.id";
        ArrayList<Wood> wood = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    String name = rs.getString("name");
                    String unit = rs.getString("unit");
                    String category = rs.getString("category");
                    int width = rs.getInt("width");
                    int length = rs.getInt("length");
                    int height = rs.getInt("height");
                    double price = rs.getInt("price");
                    boolean isPressureTreated = rs.getBoolean("is_pressure_treated");
                    wood.add(new Wood(name, length, price, unit, category, width, height, isPressureTreated));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error getting all wood");
        }
        return wood;
    }

    protected static ArrayList<Screw> getAllScrews(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select screw.name, screw.length, screw.price, screw.diameter, u.unit from screw join unit u on screw.unit = u.id";
        ArrayList<Screw> screws = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    String name = rs.getString("name");
                    String unit = rs.getString("unit");
                    int length = rs.getInt("length");
                    double price = rs.getInt("price");
                    int diameter = rs.getInt("diameter");
                    screws.add(new Screw(name, length, price, unit, diameter));
                }
            }
        } catch(SQLException e) {
         throw new DatabaseException("Error getting all screws");
        }
        return screws;
    }

    protected static ArrayList<RoofTile> getAllRoofTiles(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select roof_tile.name, roof_tile.length, roof_tile.price, roof_tile.width, u.unit from roof_tile join unit u on roof_tile.unit = u.id";
        ArrayList<RoofTile> roofTiles = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    String name = rs.getString("name");
                    String unit = rs.getString("unit");
                    double price = rs.getInt("price");
                    int width = rs.getInt("width");
                    int length = rs.getInt("length");
                    roofTiles.add(new RoofTile(name, length, price, unit, width));
                }
            }
        } catch(SQLException e) {
         throw new DatabaseException("Error getting all roof tiles");
        }
        return roofTiles;
    }

    protected static ArrayList<Fitting> getAllFittings(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select fitting.name, fitting.length, fitting.price, fitting.width, u.unit from fitting join unit u on fitting.unit = u.id";
        ArrayList<Fitting> fittings = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    String name = rs.getString("name");
                    String unit = rs.getString("unit");
                    double price = rs.getInt("price");
                    int width = rs.getInt("width");
                    int length = rs.getInt("length");
                    int height = rs.getInt("height");
                    fittings.add(new Fitting(name, length, price, unit, width, height));
                }
            }
        } catch(SQLException e) {
         throw new DatabaseException("Error getting all fittings");
        }
        return fittings;
    }

    protected static Wood getWoodById(int id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select wood.name, wood.height, wood.width, wood.length, wood.is_pressure_treated, wood.price, c.category, u.unit from wood join category c on wood.category = c.id join unit u on wood.unit = u.id where id = ?";
        Wood wood = null;
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    String name = rs.getString("name");
                    String unit = rs.getString("unit");
                    String category = rs.getString("category");
                    int width = rs.getInt("width");
                    int length = rs.getInt("length");
                    int height = rs.getInt("height");
                    double price = rs.getInt("price");
                    boolean isPressureTreated = rs.getBoolean("is_pressure_treated");
                    wood = new Wood(name, length, price, unit, category, width, height, isPressureTreated);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error getting wood by id");
        }
        return wood;
    }

    protected static Screw getScrewById(int id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select screw.name, screw.length, screw.price, screw.diameter, u.unit from screw join unit u on screw.unit = u.id where screw.id = ?";
        Screw screw = null;
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    String name = rs.getString("name");
                    String unit = rs.getString("unit");
                    int length = rs.getInt("length");
                    double price = rs.getInt("price");
                    int diameter = rs.getInt("diameter");
                    screw = new Screw(name, length, price, unit, diameter);
                }
            }
        } catch(SQLException e) {
         throw new DatabaseException("Error getting screw by id");
        }
        return screw;
    }

    protected static RoofTile getRoofTileById(int id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select roof_tile.name, roof_tile.length, roof_tile.price, roof_tile.width, u.unit from roof_tile join unit u on roof_tile.unit = u.id where roof_tile.id = ?";
        RoofTile roofTile = null;
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    String name = rs.getString("name");
                    String unit = rs.getString("unit");
                    double price = rs.getInt("price");
                    int width = rs.getInt("width");
                    int length = rs.getInt("length");
                    roofTile = new RoofTile(name, length, price, unit, width);
                }
            }
        } catch(SQLException e) {
         throw new DatabaseException("Error getting roof tile by id");
        }
        return roofTile;
    }

    protected static Fitting getFittingById(int id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select fitting.name, fitting.length, fitting.price, fitting.width, u.unit from fitting join unit u on fitting.unit = u.id where fitting.id = ?";
        Fitting fitting = null;
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    String name = rs.getString("name");
                    String unit = rs.getString("unit");
                    double price = rs.getInt("price");
                    int width = rs.getInt("width");
                    int length = rs.getInt("length");
                    int height = rs.getInt("height");
                    fitting = new Fitting(name, length, price, unit, width, height);
                }
            }
        } catch(SQLException e) {
         throw new DatabaseException("Error getting fitting by id");
        }
        return fitting;
    }

//    protected static AMaterial getMaterialById(int id, ConnectionPool connectionPool) throws DatabaseException {
//        String sql = "select material.*, type.type as name, packaging.type as packaging, material_type.material as material_type from material \n" +
//                "inner join type on material.type = type.id \n" +
//                "inner join packaging on material.packaging = packaging.id \n" +
//                "inner join material_type on material.material_type_id = material_type.id \n" +
//                "where material.id = ?";
//        try(Connection connection = connectionPool.getConnection()) {
//            try(PreparedStatement ps = connection.prepareStatement(sql)) {
//                ps.setInt(1, id);
//                ResultSet rs = ps.executeQuery();
//                while(rs.next()) {
//                    String name = rs.getString("name");
//                    String packaging = rs.getString("packaging");
//                    String description = rs.getString("description");
//                    int width = rs.getInt("width");
//                    int length = rs.getInt("length");
//                    int height = rs.getInt("height");
//                    double price = rs.getInt("price");
//                    int diameter = rs.getInt("diameter");
//                    int quantity = rs.getInt("quantity");
//                    String materialType = rs.getString("material_type");
//                    switch(materialType) {
//                        case "wood":
//                           return new Wood(name, length, price, packaging, description, width, height);
//                        case "screw":
//                            return new Screw(name, length, price, packaging, description, diameter);
//                        case "roof tile":
//                            return new RoofTile(name, length, price, packaging, description, width, height);
//                        case "fitting":
//                            return new Fitting(name, length, price, packaging, description, width, height);
//                        case "screwPack":
//                            return new ScrewPack(name, length, price, packaging, description, diameter, quantity);
//                        default:
//                            throw new DatabaseException("Unknown material type");
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new DatabaseException(e, "Error getting a material");
//        }
//
//        return null;
//    }
}
