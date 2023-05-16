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
            e.printStackTrace();
        }
        return null;
    }

    public static User updateUser(int id, String firstName, String lastName, String email, String password, String address, int phoneNumber, int roleId, int membershipId, int zip, ConnectionPool connectionPool) throws DatabaseException
    {
       User user = UserFacade.updateUser(id, firstName, lastName, email, password, address, phoneNumber, roleId, membershipId, zip, connectionPool);
       return user;
    }

    public static void createUser(String firstName, String lastName, String email, String password, String address, int phoneNumber, int roleId,  ConnectionPool connectionPool) throws DatabaseException
    {
     UserFacade.createUser(firstName, lastName, email, password, address, phoneNumber, roleId, connectionPool);


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
