package dat.backend.model.services;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;
import dat.backend.model.persistence.UserMapper;

import java.util.ArrayList;

public class UserService {

    public void createUser( String first_name, String last_name, String email, String password, String address, int phone_number, int role_id, int membership_id, int zip, ConnectionPool connectionPool) throws DatabaseException {
        UserFacade.createUser( first_name, last_name, email, password, address, phone_number, role_id, membership_id, zip, connectionPool);
    }

    public void updateUser(int id, String first_name, String last_name, String email, String password, String address, int phone_number, int role_id, int membership_id, int zip, ConnectionPool connectionPool) throws DatabaseException {
        UserFacade.updateUser(id, first_name, last_name, email, password, address, phone_number, role_id, membership_id, zip,  connectionPool);

    }



    public User getUserById(int id, ConnectionPool connectionPool) throws DatabaseException {
        UserFacade.getUser(id, connectionPool);
        return null;
    }



    public static ArrayList<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException {
        UserFacade.getAllUsers(connectionPool);
        return null;
    }

    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException {
        UserFacade. login(email, password, connectionPool);

        return null;

    }



}
