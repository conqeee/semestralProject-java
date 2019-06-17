package cz.muni.fi.pb162.project.comparator;

import cz.muni.fi.pb162.project.geometry.Vertex2D;

import java.util.Comparator;

/**
 * class representing external comparator
 */
public class VertexInverseComparator implements Comparator<Vertex2D> {
    /**
     * inversed comparing due to vertex2d comparism
     * @param o1 first object
     * @param o2 second object
     * @return +/-/0 int
     */
    @Override
    public int compare(Vertex2D o1, Vertex2D o2) {
        return -(o1.compareTo(o2));
    }
}
