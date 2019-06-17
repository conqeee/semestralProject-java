package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.utils.SimpleMath;

import java.util.Collection;

/**
 * class represents simple polygon
 */
public abstract class SimplePolygon implements Polygon {
    /**
     * basic contructor
     */
    public SimplePolygon(){
    }

    /**
     * constructor checking the illegal argumentrs - array is null or some vertice in array is null throws exception
     * @param vertices array of vertices
     */
    public SimplePolygon(Vertex2D[] vertices){
        if (vertices==null){
            throw new IllegalArgumentException("Zly argument index out of range");
        }
        for (Vertex2D vertice : vertices){
            if (vertice==null){
                throw new IllegalArgumentException("Zly argument some vertex is null");
            }
        }
    }

    /**
     * setting array of vertices of collection - getVertices
     * using simple math to find the max and min Y vertice
     * @return double number of height
     */
    @Override
    public double getHeight() {
        Vertex2D[] vertices = getVertices().toArray(new Vertex2D[0]);
        return SimpleMath.maxY(vertices).getY()-SimpleMath.minY(vertices).getY();
    }

    /**
     * similar to getHeight but using X coordinates
     * @return number of width
     */
    @Override
    public double getWidth() {

        Vertex2D[] vertices = getVertices().toArray(new Vertex2D[0]);
        return SimpleMath.maxX(vertices).getX()-SimpleMath.minX(vertices).getX();
    }

    /**
     * to string
     * appending all vertices
     * @return
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Polygon: vertices =");
        for (Vertex2D vertex : getVertices()){
            sb.append(" ").append(vertex);
        }
        return sb.toString();
    }

    /**
     * abstract
     * @param index vertex index, not negative number
     * @return the vertex of some index
     * @throws IllegalArgumentException
     */
    @Override
    public abstract Vertex2D getVertex(int index) throws IllegalArgumentException;

    /**
     * getting number of vertices
     * @return int number of vertices
     */
    @Override
    public abstract int getNumVertices();

    /**
     * setting a collection
     * @return list of vertices
     */
    @Override
    public abstract Collection<Vertex2D> getVertices();


}
