package dat.backend.model.persistence;

import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.MaterialFacade;
import dat.backend.model.services.OrderService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MaterialMapperTest {

    private final static String USER = "dev";
    private final static String PASSWORD = "3r!DE32*/fDe";
    private final static String URL = "jdbc:mysql://167.71.46.141/carport_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;

    @BeforeAll
    public static void setUpClass()
    {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);

        try (Connection testConnection = connectionPool.getConnection())
        {
            try (Statement stmt = testConnection.createStatement()) {
                // Create test database - if not exist
                stmt.execute("CREATE DATABASE  IF NOT EXISTS carport_test;");

                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.unit LIKE carport.unit;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.category LIKE carport.category;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.wood LIKE carport.wood;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.screw LIKE carport.screw;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.roof_tile LIKE carport.roof_tile;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.fitting LIKE carport.fitting;");

                stmt.execute("delete from unit");
                stmt.execute("delete from category");
                stmt.execute("delete from wood");
                stmt.execute("delete from screw");
                stmt.execute("delete from roof_tile");
                stmt.execute("delete from fitting");

                stmt.execute("ALTER TABLE unit AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE category AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE wood AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE screw AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE roof_tile AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE fitting AUTO_INCREMENT = 1;");

                stmt.execute("INSERT INTO `carport_test`.`unit` (`unit`) VALUES ('stk'), ('pakke'), ('rulle'), ('sæt')");

                stmt.execute("insert into carport_test.category(category) values ('brædt'), ('lægte'), ('reglar'), ('spærtræ'), ('stolpe');");

                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (360, 200, 25, 1, 1, 1, 174.43);");
                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (540, 200, 25, 1, 1, 1, 262.03);");
                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (360, 125, 25, 1, 1, 1, 108.90);");
                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (540, 125, 25, 1, 1, 1, 163.35);");

                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (420, 73, 38, 0, 2, 1, 120.13);");

                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (270, 95, 45, 0, 3, 1, 5628);");
                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (240, 95, 45, 0, 3, 1, 56.28);");

                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (480, 195, 45, 0, 4, 1, 205.44);");
                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (600, 195, 45, 0, 4, 1, 257.70);");

                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (300, 97, 97, 1, 5, 1, 134.85);");
                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (210, 100, 19, 1, 1, 1, 20.16);");
                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (540, 100, 19, 1, 1, 1, 51.84);");
                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (360, 100, 19, 1, 1, 1, 34.56);");

                stmt.execute("INSERT INTO `carport_test`.`roof_tile` (`name`, `length`, `width`, `unit`, `price`) VALUES ('Plastmo Ecolite blåtonet', 600, 109, 1, 633.00);");
                stmt.execute("INSERT INTO `carport_test`.`roof_tile` (`name`, `length`, `width`, `unit`, `price`) VALUES ('Plastmo Ecolite blåtonet', 360, 109, 1, 266);");

                stmt.execute( "INSERT INTO `carport`.`screw` (`name`, `diameter`, `length`, `unit`, `price`) VALUES ('plastmo bundskruer 200 stk', 0, 30, 2, 441");
                stmt.execute( "INSERT INTO `carport`.`screw` (`name`, `diameter`, `length`, `unit`, `price`) VALUES ('skruer 200 stk.', 4.5, 60, 2, 307.95");
                stmt.execute( "INSERT INTO `carport`.`screw` (`name`, `diameter`, `length`, `unit`, `price`) VALUES ('Skruer 400 stk.', 4.5, 70, 2, 149.95");
                stmt.execute( "INSERT INTO `carport`.`screw` (`name`, `diameter`, `length`, `unit`, `price`) VALUES ('beslagskruer 250 stk.', 4.5, 50, 2, 160.95");
                stmt.execute( "INSERT INTO `carport`.`screw` (`name`, `diameter`, `length`, `unit`, `price`) VALUES ('Skruer 300 stk.', 4.0, 50, 2, 263.95");
                stmt.execute( "INSERT INTO `carport`.`screw` (`name`, `diameter`, `length`, `unit`, `price`) VALUES ('bræddebolt', 10, 120, 2, 464");
                stmt.execute( "INSERT INTO `carport`.`fitting` (`name`, `width`, `length`, `height`, `unit`, `price`) VALUES ('firkantskiver', 40, 40, 11, 1, 149.50");
                stmt.execute( "INSERT INTO `carport`.`fitting` (`name`, `width`, `length`, `height`, `unit`, `price`)VALUES ('hulbånd', 20, 10000, 1, 3, 339");
                stmt.execute( "INSERT INTO `carport`.`fitting` (`name`,`height`, `unit`, `price`) VALUES ('hulbånd',190, 1, 76.95");
                stmt.execute( "INSERT INTO `carport`.`fitting` (`name`,`height`, `unit`, `price`) VALUES ('hulbånd',190, 1, 76.95");
                stmt.execute( "INSERT INTO `carport`.`fitting` (`name`, `width`, `height`, `unit`, `price`) VALUES ('stalddørsgreb', 75, 50, 4, 183.95");
                stmt.execute( "INSERT INTO `carport`.`fitting` (`name`, `height`, `unit`, `price`) VALUES ('t hængsel', 390, 2, 156.95");
                stmt.execute( "INSERT INTO `carport`.`fitting` (`name`, `height`, `unit`, `price`) VALUES ('vinkelbeslag', 35, 1, 13.95");

                /*stmt.execute("INSERT INTO `carport_test`.`screw` (`name`, `diameter`, `length`, `unit`, `price`) VALUES ('plastmo bundskruer 200 stk', 0, 30, 2, 441);");
                  stmt.execute("INSERT INTO `carport_test`.`screw` (`name`, `diameter`, `length`, `unit`, `price`) VALUES ('skruer 200 stk.', 4.5, 60, 2, 307.95);");

                stmt.execute("INSERT INTO `carport_test`.`fitting` (`name`, `width`, `length`, `height`, `unit`, `price`) VALUES ('firkantskiver', 40, 40, 11, 1, 149.50);");
                stmt.execute("INSERT INTO `carport_test`.`fitting` (`name`, `width`, `length`, `height`, `unit`, `price`) VALUES ('hulbånd', 20, 10000, 1, 3, 339);");
               */
            }
        }
        catch (SQLException throwables)
        {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException
    {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null)
        {
            connection.close();
        }
    }

    @Test
    void generateOrder() throws DatabaseException {
        Carport carport = new Carport(780, 600, new Shed(210, 530));
//        Carport carport = new Carport(480, 300);

        List<OrderItem> list = OrderService.generateOrder(carport, connectionPool);
        for (OrderItem orderItem : list) {
            System.out.println(orderItem);
        }

    }

    @Test
    void getWood() throws DatabaseException {
        Wood mat = (Wood) MaterialFacade.getWoodById(1, connectionPool);
        assertInstanceOf(Wood.class, mat);
    }

    @Test
    void getWoodList() throws DatabaseException {
        ArrayList<Wood> woodList = MaterialFacade.getAllWood(connectionPool);
        assertEquals(2, woodList.size());
    }

    @Test
    void getRoofTile() throws DatabaseException {
        RoofTile mat = (RoofTile) MaterialFacade.getRoofTileById(1, connectionPool);
        assertInstanceOf(RoofTile.class, mat);
    }

    @Test
    void getRoofTileList() throws DatabaseException {
        ArrayList<RoofTile> roofTileList = MaterialFacade.getAllRoofTiles(connectionPool);
        assertEquals(2, roofTileList.size());
    }

    @Test
    void getScrew() throws DatabaseException {
        Screw mat = (Screw) MaterialFacade.getScrewById(1, connectionPool);
        assertInstanceOf(Screw.class, mat);
    }

    @Test
    void getScrewList() throws DatabaseException {
        ArrayList<Screw> screwList = MaterialFacade.getAllScrews(connectionPool);
        assertEquals(2, screwList.size());
    }

    @Test
    void getFitting() throws DatabaseException {
        Fitting mat = (Fitting) MaterialFacade.getFittingById(1, connectionPool);
        assertInstanceOf(Fitting.class, mat);
    }

    @Test
    void getFittingList() throws DatabaseException {
        ArrayList<Fitting> fittingList = MaterialFacade.getAllFittings(connectionPool);
        assertEquals(2, fittingList.size());
    }

    @Test
    void getAllMaterials() throws DatabaseException {
        ArrayList<AMaterial> materialList = MaterialFacade.getAllMaterials(connectionPool);
        assertEquals(8, materialList.size());
    }


}
