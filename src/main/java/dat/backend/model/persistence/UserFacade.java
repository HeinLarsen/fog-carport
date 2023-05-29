package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserFacade
{

// TODO refactor return types to return the data we receive from the database instead of void
    public static ArrayList<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException
    {
        return UserMapper.getAllUsers(connectionPool);
    }

    public static User getUser(int id, ConnectionPool connectionPool) throws DatabaseException
    {
        return UserMapper.getUser(id, connectionPool);
    }

    public static User updateUser(int id, String firstName, String lastName, String email, String password, String address, int phoneNumber, int roleId, int membershipId, int zip, String city, ConnectionPool connectionPool) throws DatabaseException
    {
       User user = UserMapper.updateUser(id, firstName, lastName, email, password, address, phoneNumber, roleId, membershipId, zip, city, connectionPool);
       return user;
    }

   public static void createUser2(User u, ConnectionPool connectionPool) throws DatabaseException
    {
       UserMapper.createUser(u, connectionPool);

    }

    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException {
        User user = null;
       user = UserMapper.login(email, password, connectionPool);
        return user;
    }


    public static void createZipCity(User u, ConnectionPool connectionPool) throws DatabaseException {
        UserMapper.createZipCity(u, connectionPool);
    }
}
