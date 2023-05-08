package dat.backend.model.persistence;

import dat.backend.model.entities.Material;
import dat.backend.model.entities.Wood;
import dat.backend.model.exceptions.DatabaseException;

import java.util.ArrayList;

public class MaterialFacade {

    public static ArrayList<Material> getAllMaterials(ConnectionPool connectionPool) throws DatabaseException {
        return MaterialMapper.getAllMaterials(connectionPool);
    }

    public static void addWood(Wood wood, ConnectionPool connectionPool) throws DatabaseException {
        MaterialMapper.addWood(wood, connectionPool);
    }



}
