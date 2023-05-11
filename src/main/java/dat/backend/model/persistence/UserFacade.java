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

    public static void updateUser(int id, String first_name, String last_name, String email, String password, String address, int phone_number, int role_id, int membership_id, int zip, ConnectionPool connectionPool) throws DatabaseException
    {
        UserMapper.updateUser(id, first_name, last_name, email, password, address, phone_number, role_id, membership_id, zip, connectionPool);
    }

   public static void createUser( int id, String first_name, String last_name, String email, String password, String address, int phone_number, int role_id, int membership_id, int zip, ConnectionPool connectionPool) throws DatabaseException
    {
        UserMapper.createUser(id, first_name, last_name, email, password, address, phone_number, role_id, membership_id, zip, connectionPool);
    }

    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException, SQLException {
        User user = null;
       user = UserMapper.login(email, password, connectionPool);
        return user;
    }


}
