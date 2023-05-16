package dat.backend.model.services;

import dat.backend.model.entities.Wood;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

import java.io.IOException;
import java.util.ArrayList;


public class Moddeling
{


    Geometry3D buildbar (int length, int width, int height) throws IOException
    {
        Wood wood = new Wood("test", 40, 50, "stk", "test", 10, 10, true);
        ArrayList<Wood> woodlist;
        JavaCSG csg = JavaCSGFactory.createDefault();
        int carportlength = 7;
        double woodamount = carportlength / 0.9;
        Geometry3D finishedbar = null;
        double x= 12;
        double y=0;
        for (double i = 0; i < woodamount; i++)
        {
            Geometry3D bar = csg.box3D(length, width, height, true);
            bar = csg.translate3D(x, y, 0).transform(bar);
            finishedbar = csg.union3D(bar);

        }
        csg.view(finishedbar);
        csg.saveSTL("src/main/webapp/3d-Models\\test.stl", finishedbar);
        return finishedbar;
    }



   /* Geometry3D buildroof (int length, int width){
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
*/
}

