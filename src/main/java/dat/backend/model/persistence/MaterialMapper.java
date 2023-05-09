package dat.backend.model.persistence;

import dat.backend.model.entities.Material;
import dat.backend.model.exceptions.DatabaseException;

import java.util.ArrayList;

public class MaterialMapper {
    protected static ArrayList<Material> getAllMaterials(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select * from material inner join type on material.type = type.id";
    }
}
