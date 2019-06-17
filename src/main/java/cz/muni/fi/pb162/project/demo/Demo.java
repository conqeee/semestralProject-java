package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.geometry.GeneralRegularPolygon;
import cz.muni.fi.pb162.project.geometry.Vertex2D;

/**
 * @author Miroslav Trzil
 */

/**
 * Demo class used for calling methods
 */
public class Demo {

    /**
     * creating a octagon with middle [0,0] and radius 1
     * printing the regular octagon
     * @param args arguments from cmd line
     */
    public static void main(String[] args) {
        Vertex2D middle = new Vertex2D(0,0);
        final GeneralRegularPolygon octagon = new GeneralRegularPolygon(middle,8,1);
        System.out.println(octagon.toString());
    }

    /*Double compare(d1,d2)
    Double hashCode(d1)
    Arrays.asList(arr[])

     */

}
