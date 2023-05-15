package dat.backend.model.persistence;

import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MaterialFacade {

    public static ArrayList<AMaterial> getAllMaterials(ConnectionPool connectionPool) throws DatabaseException {
        ArrayList<AMaterial> materials = new ArrayList<>();
        materials.addAll(MaterialMapper.getAllWood(connectionPool));
        materials.addAll(MaterialMapper.getAllScrews(connectionPool));
        materials.addAll(MaterialMapper.getAllFittings(connectionPool));
        materials.addAll(MaterialMapper.getAllRoofTiles(connectionPool));
        return materials;
    }

    public static Wood getWoodById(int id, ConnectionPool connectionPool) throws DatabaseException {
        return MaterialMapper.getWoodById(id, connectionPool);
    }

    public static Screw getScrewById(int id, ConnectionPool connectionPool) throws DatabaseException {
        return MaterialMapper.getScrewById(id, connectionPool);
    }

    public static Fitting getFittingById(int id, ConnectionPool connectionPool) throws DatabaseException {
        return MaterialMapper.getFittingById(id, connectionPool);
    }

    public static RoofTile getRoofTileById(int id, ConnectionPool connectionPool) throws DatabaseException {
        return MaterialMapper.getRoofTileById(id, connectionPool);
    }

    public static ArrayList<Wood> getAllWood(ConnectionPool connectionPool) throws DatabaseException {
        return MaterialMapper.getAllWood(connectionPool);
    }

    public static ArrayList<Screw> getAllScrews(ConnectionPool connectionPool) throws DatabaseException {
        return MaterialMapper.getAllScrews(connectionPool);
    }

    public static ArrayList<Fitting> getAllFittings(ConnectionPool connectionPool) throws DatabaseException {
        return MaterialMapper.getAllFittings(connectionPool);
    }

    public static ArrayList<RoofTile> getAllRoofTiles(ConnectionPool connectionPool) throws DatabaseException {
        return MaterialMapper.getAllRoofTiles(connectionPool);
    }



}
