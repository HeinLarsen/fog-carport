package dat.backend.persistence;

import dat.backend.model.entities.AMaterial;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.MaterialFacade;
import dat.backend.model.persistence.OrderItemFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
            try (Statement stmt = testConnection.createStatement())
            {
                // Create test database - if not exist
                stmt.execute("CREATE DATABASE  IF NOT EXISTS carport_test;");

                // TODO: Create user table. Add your own tables here
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.material_type LIKE carport.material_type;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.packaging LIKE carport.packaging;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.type LIKE carport.type;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.material LIKE carport.material;");

            }
        }
        catch (SQLException throwables)
        {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @BeforeEach
    void setUp()
    {
        try (Connection testConnection = connectionPool.getConnection())
        {
            try (Statement stmt = testConnection.createStatement())
            {
                stmt.execute("delete from material_type");
                stmt.execute("delete from packaging");
                stmt.execute("delete from type");
                stmt.execute("delete from material");


                stmt.execute("INSERT INTO packaging (type) VALUES ('stk'), ('pakke'), ('rulle'), ('sæt');");
                stmt.execute("INSERT INTO type (type) VALUES ('trykimp. brædt'), ('lægte'), ('reglar ub.'), ('spærtræ ubh.'), ('trykimp. Brædt'), ('Plastmo Ecolite blåtonet'), ('Plastmo Trapez Brundskrue'), ('hulbånd'), ('universal'), ('universal'), ('skruer'), ('beslagskruer'), ('bræddebolt'), ('firkantskiver'), ('stalddørsgreb'), ('t hængse'), ('vinkelbeslag');");
                stmt.execute("insert into material_type (material) value ('wood'), ('screw'), ('screwPack'), ('roof tile'), ('fitting'),('door handle');");

                stmt.execute("INSERT INTO `material` (`length`, `width`, `height`, `description`, `price`, `type`, `packaging`, `material_type_id`) VALUES ('360', '200', '25', 'understærnbrædder til for og bag ende', '174.43', '1', '1', '1 ');");
                stmt.execute("INSERT INTO `material` (`length`, `width`, `diameter`, `description`, `price`, `type`, `packaging`, `material_type_id`) VALUES ('120', '120', '10', 'Til montering af rem på stolper', '464.00', '13', '1', '2');");
                stmt.execute("INSERT INTO `material` (`length`, `description`, `quantity`, `price`, `type`, `packaging`, `material_type_id`) VALUES ('30', 'skruer til tagplader', '200', '441.00', '7', '2', '3');");
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

    @Test
    void getWood() throws DatabaseException
    {
        AMaterial mat = MaterialFacade.getMaterialById(1, connectionPool);
        assertNotNull(mat);
        assertInstanceOf(AMaterial.class, mat);

    }


}
