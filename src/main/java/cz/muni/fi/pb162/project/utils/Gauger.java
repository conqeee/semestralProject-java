package cz.muni.fi.pb162.project.utils;

import cz.muni.fi.pb162.project.geometry.Measurable;
import cz.muni.fi.pb162.project.geometry.Triangle;

/**
 * represents the gauger - counter
 */
public class Gauger {
    /**
     * when its Measurable param, prints its width and height
     * @param measurable object using Measurable interface
     */
    public static void printMeasurement(Measurable measurable){
        System.out.println("Width: "+measurable.getWidth());
        System.out.println("Height: "+measurable.getHeight());
    }

    /**
     * its its Triangle object, it prints triangle vertices + width + height
     * @param triangle the Triangle object
     */
    public static void printMeasurement(Triangle triangle){
        System.out.println(triangle.toString());
        Gauger.printMeasurement((Measurable) triangle);
        }
}
