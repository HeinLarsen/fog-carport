package dat.backend.model.services;

import dat.backend.model.entities.Wood;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Moddeling
{


    public static void main(String[] args) throws IOException
    {
        Moddeling model = new Moddeling();
        model.buildbar(40, 10, 40);
    }



    Geometry3D buildbar(int length, int width, int height) throws IOException
    {
        JavaCSG csg = JavaCSGFactory.createDefault();
        Geometry3D finishedbar = null;
        ArrayList<Geometry3D> bars = new ArrayList<>();
        Geometry3D finishedbar2 = null;
        for (int i = 0; i < 10; i++)
        {
            csg = JavaCSGFactory.createDefault();
            Geometry3D bar = csg.box3D(length, width, height, true);
            finishedbar = csg.union3D(bar);
            double x = 0;
            double y = 40;
            csg.translate3D(x, y, 0).transform(finishedbar);
            bars.add(finishedbar);
            finishedbar2 = csg.union3D(bars.get(i));

        }
        csg.saveSTL("src/main/webapp/3d-Models\\test.stl", finishedbar2);
        return finishedbar;

    }




   Geometry3D buildroof (int length, int width){
        JavaCSG csg = JavaCSGFactory.createDefault();
        Geometry3D roof = csg.box3D(length, width, 0.3, true);
        Geometry3D finishedroof = csg.union3D(roof);
        csg.view(finishedroof);
        return finishedroof;
    }
/*

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


