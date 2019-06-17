package cz.muni.fi.pb162.project.geometry;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Class represents polygon in array
 */
public class ArrayPolygon extends SimplePolygon implements Inverter{
    private Vertex2D[] vertices;

    /**
     * constructor making polygon via array of vertices
     * checks if some vertex is not null or a array is not null
     * than making a copy of that array
     * @param vertices the vertices array
     */
    public ArrayPolygon(Vertex2D[] vertices){
        super(vertices);
        this.vertices = Arrays.copyOf(vertices, vertices.length);
    }

    /**
     * making array as list
     * @return array as list of vertices
     */
    @Override
    public Collection<Vertex2D> getVertices() {
        return Arrays.asList(vertices);
    }

    /**
     * getting vertex on some index of array - we count the index modulo number of vertices
     * @param index position
     * @return vertex on index position of array
     * @throws IllegalArgumentException if index is bellow 0
     */
    @Override
    public Vertex2D getVertex(int index) throws IllegalArgumentException {
        if (index<0){
            throw new IllegalArgumentException("index cant be bellow 0.");
        }
        return vertices[index % vertices.length];
    }

    /**
     * getting number of vertices
     * @return number of vertices
     */
    @Override
    public int getNumVertices() {
        return vertices.length;
    }

    /**
     * returns inverted polygon array using list -> collection method reverse -> back to array
     * @return inverted polygon array
     */
    @Override
    public Polygon invert() {
        List<Vertex2D> listOfVertices = Arrays.asList(vertices);
        Collections.reverse(listOfVertices);
        return new ArrayPolygon(listOfVertices.toArray(new Vertex2D[0]));
    }

    /**
     * making hash - additing hash of each vertex
     * @return number - hash
     */
    @Override
    public int hashCode() {
        int result = 0;
        for (Vertex2D vertex : vertices){
            result += vertex.hashCode()*27;
        }
        return result;
    }

    /**
     * private function returns the index of array which vertex is the same with the first positioned vertex in first
     * array
     * @param firstVertex the first vertex in first array
     * @param second array of polygons
     * @return index of position or -1 when there is not same vertex
     */
    private int gettinIndex(Vertex2D firstVertex,ArrayPolygon second){

        for (int i=0; i<vertices.length; i++){
            if (firstVertex.equals(second.getVertex(i))){
                return i;
            }
        }
        return -1;
    }

    /**
     * equals method
     * first if - if they are same, its true
     * second if object is null or the classes are different - its false
     * setting the first vertex
     * if gettinIndex is -1 its false
     * than comparing second positioned vertex in first with the right neightbour of second similar - if they arent
     * same, we invert second and search another index of similar vertex in second
     * than using for cycle to comparing the right neightbours, if some of neightbour doesnt match, its false
     * if match, than its true
     * @param object second compared object
     * @return true or false
     */
    public boolean equals(Object object){
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }

        ArrayPolygon second = (ArrayPolygon) object;

        Vertex2D firstVertex= this.vertices[0];
        int indexSimilarToFirst = gettinIndex(firstVertex,second);
        if (indexSimilarToFirst==-1){
            return false;
        }

        if(!(getVertex(1).equals(second.getVertex(indexSimilarToFirst+1)))){
            second.invert();
            indexSimilarToFirst = gettinIndex(firstVertex,second);
        }

        for (int i = 0; i<vertices.length;i++){
            if (!(getVertex(i).equals(second.getVertex(indexSimilarToFirst+i)))){
                return false;
            }
        }
        return true;



    }

}
