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
         UserFacade.login(email, password, connectionPool);
            return null;
    }



}
