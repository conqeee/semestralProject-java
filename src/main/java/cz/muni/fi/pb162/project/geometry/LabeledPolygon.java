package cz.muni.fi.pb162.project.geometry;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.Comparator;

/**
 * class represents labeled polygon - using maps
 */
public class LabeledPolygon extends SimplePolygon implements Labelable, Sortable{
    private Map<String,Vertex2D> vertices = new TreeMap();

    /**
     * constructor usiing super constructor with empty array
     */
    public LabeledPolygon() {
        super(new Vertex2D[0]);
    }

    /**
     * getting vertex on determined position in array
     * @param index vertex index, not negative number
     * @return vertex on position index
     * @throws IllegalArgumentException when index is bellow 0
     */
    @Override
    public Vertex2D getVertex(int index) throws IllegalArgumentException {
        if (index<0){
            throw new IllegalArgumentException("index cannot be bellow 0");
        }
        List<Vertex2D> allVertices = new ArrayList<>();
        allVertices.addAll(vertices.values());
        return allVertices.get(index%getNumVertices());
    }

    /**
     * gets number of vertices in map
     * @return  num of vertices
     */
    @Override
    public int getNumVertices() {
        return vertices.size();
    }

    @Override
    public Collection<Vertex2D> getVertices() {
        List<Vertex2D> allVertices = new ArrayList<>();
        allVertices.addAll(vertices.values());
        return Collections.unmodifiableCollection(allVertices);
    }

    /**
     * adding vertex into map
     * @param label  label of the vertex
     * @param vertex the actual vertex object
     * @throws IllegalArgumentException when label is null or vertex is null
     */
    @Override
    public void addVertex(String label, Vertex2D vertex) throws IllegalArgumentException {
        if (label == null){
            throw new IllegalArgumentException("label of vertex cannot be null");
        }
        if (vertex == null){
            throw new IllegalArgumentException("vertex cannot be null");
        }
        vertices.put(label, vertex);
    }

    /**
     * getting vertex which key is equals to determined key
     * @param label label under which the vertex is stored
     * @return vertex
     */
    @Override
    public Vertex2D getVertex(String label) {
        if (!vertices.containsKey(label)){
            throw new NullPointerException("map doesnt contain specified key");
        }
        return vertices.get(label);
    }

    /**
     * getting all labels
     * @return collection of labels
     */
    @Override
    public Collection<String> getLabels() {
        return Collections.unmodifiableSet(vertices.keySet());
    }

    /**
     * getting set of labels which has same vertex
     * @param vertex vertex which labels are searched
     * @return set made of strings - the keys
     */
    @Override
    public Collection<String> getLabels(Vertex2D vertex) {
        Set<String> labelsWithVertex = new HashSet<>();
        for (String label : getLabels()){
            if (getVertex(label).equals(vertex)){
                labelsWithVertex.add(label);
            }
        }
        return labelsWithVertex;
    }

    /**
     * sorting vertices making a list, than sorting
     * @return sorted collection
     */
    @Override
    public Collection<Vertex2D> getSortedVertices() {
        List<Vertex2D> sortingVertices = new ArrayList<>();
        sortingVertices.addAll(getVertices());
        Collections.sort(sortingVertices);
        return sortingVertices;
    }

    /**
     * sorting vertices via external comparator
     * @param comparator comparator object used to determine the ordering
     * @return sorted collection
     */
    @Override
    public Collection<Vertex2D> getSortedVertices(Comparator<Vertex2D> comparator) {
        List<Vertex2D> sortedVertices = new ArrayList<>();
        sortedVertices.addAll(vertices.values());
        sortedVertices.sort(comparator);
        return sortedVertices;
    }

    /**
     * finds all duplicite vertices in array
     * @return vertices of all duplicites
     */
    public Collection<Vertex2D> duplicateVertices(){
        Set<Vertex2D> duplicates = new HashSet<>();
        List<Vertex2D> allVertices = new ArrayList<>();
        allVertices.addAll(getSortedVertices());
        for (int i = 0; i<getSortedVertices().size()-1; i++){
            if (allVertices.get(i).equals(allVertices.get(i+1))){
                duplicates.add(allVertices.get(i));
            }
        }
        return duplicates;
    }

}
