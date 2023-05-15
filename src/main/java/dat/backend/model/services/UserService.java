package dat.backend.model.services;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {

    // TODO refactor return types to return the data we receive from the database instead of void

    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException, SQLException {
        try{
            User user = UserFacade.login(email, password, connectionPool);
            return user;
        }catch (DatabaseException e){
            e.getMessage();
        }
        return null;
    }

    public static User updateUser(int id, String first_name, String last_name, String email, String password, String address, int phone_number, int role_id, int membership_id, int zip, ConnectionPool connectionPool) throws DatabaseException
    {
       User user = UserFacade.updateUser(id, first_name, last_name, email, password, address, phone_number, role_id, membership_id, zip, connectionPool);
       return user;
    }

    public static User createUser(String first_name, String last_name, String email, String password, String address, int phone_number, int role_id, int membership_id, int zip, ConnectionPool connectionPool) throws DatabaseException
    {
     User user = UserFacade.createUser(first_name, last_name, email, password, address, phone_number, role_id, membership_id, zip, connectionPool);
     return user;

    }

    public static User getUser(int id, ConnectionPool connectionPool) throws DatabaseException
    {
        return UserFacade.getUser(id, connectionPool);
    }

    public static ArrayList<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException
    {
        return UserFacade.getAllUsers(connectionPool);
    }






}
