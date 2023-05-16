package dat.backend.model.services;

import dat.backend.model.entities.User;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

import java.io.IOException;

public class Moddeling
{
    Geometry3D buildbar (int length, int width, int height){
        JavaCSG csg = JavaCSGFactory.createDefault();
        Geometry3D bar = csg.box3D(length, width, height, true);
        Geometry3D finishedbar = csg.union3D(bar);
        csg.view(finishedbar);
        return finishedbar;
    }


    Geometry3D buildroof (int length, int width){
        JavaCSG csg = JavaCSGFactory.createDefault();
        Geometry3D roof = csg.box3D(length, width, 0.3, true);
        Geometry3D finishedroof = csg.union3D(roof);
        csg.view(finishedroof);
        return finishedroof;
    }


    Geometry3D buildproduct(String filename, Geometry3D roof, Geometry3D bar){
        JavaCSG csg = JavaCSGFactory.createDefault();
        Geometry3D finishedproduct = csg.union3D(roof, bar);
        csg.view(finishedproduct);
        try
        {
            csg.saveSTL( "src/main/webapp/3d-Models\\" +filename+".stl", finishedproduct);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return finishedproduct;
    }

}

