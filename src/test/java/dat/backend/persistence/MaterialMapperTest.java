package dat.backend.persistence;

import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.MaterialFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MaterialMapperTest {

    private final static String USER = "root";
    private final static String PASSWORD = "password";
    private final static String URL = "jdbc:mysql://localhost/carport_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;

    @BeforeAll
    public static void setUpClass()
    {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);

        try (Connection testConnection = connectionPool.getConnection())
        {
            try (Statement stmt = testConnection.createStatement())
            {
                // Create test database - if not exist
                stmt.execute("CREATE DATABASE  IF NOT EXISTS carport_test;");

                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.material_type LIKE carport.material_type;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.packaging LIKE carport.packaging;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.type LIKE carport.type;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.material LIKE carport.material;");

                stmt.execute("delete from material_type");
                stmt.execute("delete from packaging");
                stmt.execute("delete from type");
                stmt.execute("delete from material");

                stmt.execute("ALTER TABLE material AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE material_type AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE packaging AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE type AUTO_INCREMENT = 1;");

                stmt.execute("INSERT INTO packaging (type) VALUES ('stk'), ('pakke'), ('rulle'), ('sæt');");
                stmt.execute("INSERT INTO type (type) VALUES ('trykimp. brædt'), ('lægte'), ('reglar ub.'), ('spærtræ ubh.'), ('trykimp. Brædt'), ('Plastmo Ecolite blåtonet'), ('Plastmo Trapez Brundskrue'), ('hulbånd'), ('universal'), ('universal'), ('skruer'), ('beslagskruer'), ('bræddebolt'), ('firkantskiver'), ('stalddørsgreb'), ('t hængse'), ('vinkelbeslag');");
                stmt.execute("insert into material_type (material) value ('wood'), ('screw'), ('screwPack'), ('roof tile'), ('fitting'),('door handle');");

                stmt.execute("INSERT INTO `material` (`length`, `width`, `height`, `description`, `price`, `type`, `packaging`, `material_type_id`) VALUES ('360', '200', '25', 'understærnbrædder til for og bag ende', '174.43', '1', '1', '1 ');");
                stmt.execute("INSERT INTO `material` (`length`, `width`, `diameter`, `description`, `price`, `type`, `packaging`, `material_type_id`) VALUES ('120', '120', '10', 'Til montering af rem på stolper', '464.00', '13', '1', '2');");
//                stmt.execute("INSERT INTO `material` (`length`, `description`, `quantity`, `price`, `type`, `packaging`, `material_type_id`) VALUES ('30', 'skruer til tagplader', '200', '441.00', '7', '2', '3');");
                stmt.execute("INSERT INTO `material` (`length`, `width`, `description`, `price`, `type`, `packaging`, `material_type_id`) VALUES ('600', '300', 'tagplader monteres på spær', '633.00', '6', '1', '4');");
                stmt.execute("INSERT INTO `material` (`length`, `width`, `diameter`, `description`, `price`, `type`, `packaging`, `material_type_id`) VALUES ('1000', '20', '1', 'til vindkryds og spær', '339.00', '8', '3', '5');");


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
//
//    @Test
//    void getWood() throws DatabaseException
//    {
//        Wood mat = (Wood) MaterialFacade.getMaterialById(1, connectionPool);
//        System.out.println(mat instanceof Wood);
//        assertEquals(360, mat.getLength());
//        assertEquals(200, mat.getWidth());
//        assertEquals(25, mat.getHeight());
//        assertEquals("understærnbrædder til for og bag ende", mat.getDescription());
//        assertInstanceOf(Wood.class, mat);
//    }
//
//    @Test
//    void getRoofTile() throws DatabaseException
//    {
//        RoofTile mat = (RoofTile) MaterialFacade.getMaterialById(4, connectionPool);
//        System.out.println(mat instanceof RoofTile);
//        assertEquals(600, mat.getLength());
//        assertEquals(300, mat.getWidth());
//        assertEquals("tagplader monteres på spær", mat.getDescription());
//        assertInstanceOf(RoofTile.class, mat);
//    }
//
//    @Test
//    void getScrew() throws DatabaseException {
//        Screw mat = (Screw) MaterialFacade.getMaterialById(3, connectionPool);
//        System.out.println(mat instanceof Screw);
//        assertEquals(30, mat.getLength());
//        assertEquals("skruer til tagplader", mat.getDescription());
//        assertEquals(200, mat.getDiameter());
//        assertInstanceOf(Screw.class, mat);
//    }
//
//    @Test
//    void getScrewPack() throws DatabaseException {
//        ScrewPack mat = (ScrewPack) MaterialFacade.getMaterialById(3, connectionPool);
//        System.out.println(mat instanceof ScrewPack);
//        assertEquals(120, mat.getLength());
//        assertEquals(300, mat.getQuantity());
//        assertEquals(10, mat.getDiameter());
//        assertInstanceOf(ScrewPack.class, mat);
//    }
//
//    @Test
//    void getFitting() throws DatabaseException {
//        Fitting mat = (Fitting) MaterialFacade.getMaterialById(5, connectionPool);
//        System.out.println(mat instanceof Fitting);
//        assertEquals(1000, mat.getLength());
//        assertEquals("til vindkryds og spær", mat.getDescription());
//        assertInstanceOf(Fitting.class, mat);
//    }
//
//    @Test
//    void getAllMaterials() throws DatabaseException {
//        ArrayList<AMaterial> materials = MaterialFacade.getAllMaterials(connectionPool);
//        assertEquals(5, materials.size());
//        for (AMaterial mat : materials) {
//            if (mat instanceof Wood) {
//                assertInstanceOf(Wood.class, mat);
//            } else if(mat instanceof Screw) {
//                assertInstanceOf(Screw.class, mat);
//            } else if(mat instanceof ScrewPack) {
//                assertInstanceOf(ScrewPack.class, mat);
//            } else if(mat instanceof RoofTile) {
//                assertInstanceOf(RoofTile.class, mat);
//            } else if(mat instanceof Fitting) {
//                assertInstanceOf(Fitting.class, mat);
//            }
//
//        }
//    }


}
