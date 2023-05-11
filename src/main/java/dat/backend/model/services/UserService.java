package dat.backend.model.services;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;
import dat.backend.model.persistence.UserMapper;

import java.util.ArrayList;

public class UserService {

    public void createUser(User user) {
        // TODO implement here
    }

    public void updateUser(User user) {
        // TODO implement here
    }

    public User getUserById(int id) {
        // TODO implement here
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
