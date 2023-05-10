package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.util.ArrayList;

public class UserFacade
{


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

   public static void createUser(String first_name, String last_name, String email, String password, String address, int phone_number, int role_id, int membership_id, int zip, ConnectionPool connectionPool) throws DatabaseException
    {
        UserMapper.createUser(first_name, last_name, email, password, address, phone_number, role_id, membership_id, zip, connectionPool);
    }

    public static void login(String email, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        UserMapper.login(email, password, connectionPool);
    }


}
