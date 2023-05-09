package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

public class UserFacade
{
    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        return UserMapper.login(email, password, connectionPool);
    }

    public static User createUser(int id, String first_name, String last_name, String email, String password, String address, int phone_number, int role_id, int membership_id, int zip,  ConnectionPool connectionPool) throws DatabaseException
    {
        return UserMapper.createUser(id, first_name, last_name, email, password, address, phone_number, role_id, membership_id, zip, connectionPool);
    }
}
