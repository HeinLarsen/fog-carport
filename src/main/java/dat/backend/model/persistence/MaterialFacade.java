package dat.backend.model.persistence;

import dat.backend.model.entities.AMaterial;
import dat.backend.model.exceptions.DatabaseException;

import java.util.ArrayList;

public class MaterialFacade {

    public static ArrayList<AMaterial> getAllMaterials(ConnectionPool connectionPool) throws DatabaseException {
        return MaterialMapper.getAllMaterials(connectionPool);
    }

    public static AMaterial getMaterialById(int id, ConnectionPool connectionPool) throws DatabaseException {
        return MaterialMapper.getMaterialById(id, connectionPool);
    }



}
