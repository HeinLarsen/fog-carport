package dat.backend.model.services;


import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

import java.io.IOException;
import java.util.ArrayList;

public class Moddeling
{


    public static void main(String[] args) throws IOException
    {
        Moddeling model = new Moddeling();
        model.buildbar3(40, 10, 40, 10);

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
        //csg.saveSTL("src/main/webapp/3d-Models\\test.stl", finishedbar2);
        csg.view(finishedbar2);
        return finishedbar;

    }

    Geometry3D buildbar2(int length, int width, int height) throws IOException {
        JavaCSG csg = JavaCSGFactory.createDefault();
        ArrayList<Geometry3D> planks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            csg = JavaCSGFactory.createDefault();
            Geometry3D plank = csg.box3D(length, width, height, true);
            double x = i * (length + 2); // Adjust the spacing between planks
            double y = 0;
            double z = 0;
            csg.translate3D(x, y, z).transform(plank);
            planks.add(plank);
        }

        Geometry3D finishedBar = csg.union3D(planks);

        //csg.saveSTL("path/to/output.stl", finishedBar);
        csg.view(finishedBar);
        return finishedBar;
    }


    Geometry3D buildbar3(int length, int width, int height, int quantity) throws IOException {
        JavaCSG csg = JavaCSGFactory.createDefault();
        ArrayList<Geometry3D> planks = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            csg = JavaCSGFactory.createDefault();
            Geometry3D plank = csg.box3D(length, width, height, true);
            double x = i * (length + 2); // Adjust the spacing between planks
            double y = 0;
            double z = 0;
            csg.translate3D(x, y, z).transform(plank);
            planks.add(plank);
        }

        Geometry3D finishedBar = csg.union3D(planks);

        //csg.saveSTL("path/to/output.stl", finishedBar);
        csg.view(finishedBar);
        return finishedBar;
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


