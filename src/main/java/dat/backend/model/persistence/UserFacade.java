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

    public static User updateUser(int id, String firstName, String lastName, String email, String password, String address, int phoneNumber, int roleId, int membershipId, int zip, ConnectionPool connectionPool) throws DatabaseException
    {
       User user = UserMapper.updateUser(id, firstName, lastName, email, password, address, phoneNumber, roleId, membershipId, zip, connectionPool);
       return user;
    }

   public static User createUser( String firstName, String lastName, String email, String password, String address, int phoneNumber, int roleId, int membershipId, int zip, ConnectionPool connectionPool) throws DatabaseException
    {
       User user = UserMapper.createUser(firstName, lastName, email, password, address, phoneNumber, zip, connectionPool);
       return user;
    }

    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException, SQLException {
        User user = null;
       user = UserMapper.login(email, password, connectionPool);
        return user;
    }


}
