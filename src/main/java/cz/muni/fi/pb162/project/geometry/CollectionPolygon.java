package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.utils.SimpleMath;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * class represents polygon in collection
 */
public class CollectionPolygon extends SimplePolygon implements Inverter{
    private List<Vertex2D> vertices = new ArrayList<>();

    /**
     * constructor for creating a a list of vertices
     * @param vertices array of vertices
     */
    public CollectionPolygon(Vertex2D[] vertices){
        super(vertices);
        this.vertices=new ArrayList<>(Arrays.asList(vertices));
    }

    /**
     * getting vertex on determined position in array
     * @param index vertex index, not negative number
     * @return Vertex2D
     * @throws IllegalArgumentException when index is not in range (<0
     */
    @Override
    public Vertex2D getVertex(int index) throws IllegalArgumentException {
        if (index<0){
            throw new IllegalArgumentException("index cant be bellow 0.");
        }
        return vertices.get(index % vertices.size());
    }

    /**
     * getting number of vertices in list
     * @return int number of vertices
     */
    @Override
    public int getNumVertices() {
        return vertices.size();
    }

    /**
     * returns unmodifiable collection vertices
     * @return collection of vertices
     */
    @Override
    public Collection<Vertex2D> getVertices() {
        return Collections.unmodifiableCollection(vertices);
    }

    /**
     * method used for inverting the list same as in the arraypolygon
     * @return new collection of polygons
     */
    @Override
    public Polygon invert() {
        List<Vertex2D> listOfVertices = new ArrayList<>(vertices);
        Collections.reverse(listOfVertices);
        return new CollectionPolygon(listOfVertices.toArray(new Vertex2D[0]));
    }

    /**
     * removing the most left vertices having two lists, one for not left vertices and second for left vertices
     * getting double of minX vertice - most left number
     * comparing all X coordinates with minX number if its equals, than goes to left array, if not going to correct
     * @return the array of leftmsot vertices
     */
    public Collection<Vertex2D> removeLeftmostVertices(){
        if (vertices.isEmpty()) {
            return Collections.emptyList();
        }
        List<Vertex2D> leftVertices = new ArrayList<>();
        List<Vertex2D> correctVertices = new ArrayList<>();
        double minimalX = SimpleMath.minX(vertices.toArray(new Vertex2D[vertices.size()])).getX();
        for (Vertex2D vertex : vertices){
            if (vertex.getX() == minimalX){
                leftVertices.add(vertex);
            } else correctVertices.add(vertex);
        }
        this.vertices=correctVertices;
        return leftVertices;
    }
}
