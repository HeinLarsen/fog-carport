package dat.backend.model.services;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {

    // TODO refactor return types to return the data we receive from the database instead of void

    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException {
      return UserFacade.login(email, password, connectionPool);
    }

    public static User updateUser(int id, String firstName, String lastName, String email, String password, String address, int phoneNumber, int roleId, int membershipId, int zip, String city, ConnectionPool connectionPool) throws DatabaseException
    {
       User user = UserFacade.updateUser(id, firstName, lastName, email, password, address, phoneNumber, roleId, membershipId, zip, city, connectionPool);
       return user;
    }

    public static void createUser(User u, ConnectionPool connectionPool) throws DatabaseException {
        UserFacade.createZipCity(u, connectionPool);
        UserFacade.createUser(u, connectionPool);

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
