package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.exceptions.EmptyDrawableException;
import cz.muni.fi.pb162.project.exceptions.MissingVerticesException;
import cz.muni.fi.pb162.project.exceptions.TransparentColorException;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * class represents the paper
 */
public class Paper implements Drawable, PolygonFactory {
    private Set<ColoredPolygon> coloredPolygons = new HashSet<>();
    private Color color = Color.BLACK;

    /**
     * basic constructor
     */
    public Paper(){
    }
    /**
     * constructor copying the collection of arrays from the paper
     * @param paper whats already on paper
     */
    public Paper(Paper paper){
        coloredPolygons = new HashSet<>(paper.coloredPolygons);
    }

    /**
     * changing color of the color
     * @param color the color which the drawn objects are going to have
     */
    @Override
    public void changeColor(Color color) {
        this.color = color;
    }

    /**
     * * polygon which is going to be drawn - if its color is white, if wont be drawn
     * @param polygon polygon to be drawn
     * @throws TransparentColorException if the color is white
     */
    @Override
    public void drawPolygon(Polygon polygon) throws TransparentColorException {
        if (color== Color.WHITE){
            throw new TransparentColorException("The color cannot be white");
        }
        coloredPolygons.add(new ColoredPolygon(polygon,color));
    }

    /**
     * removing the unwanted polygon from set
     * @param polygon polygon to be erased
     */
    @Override
    public void erasePolygon(ColoredPolygon polygon) {
        coloredPolygons.remove(polygon);
    }

    /**
     * erasing all polygons from set
     * @throws EmptyDrawableException if the colored polygon is empty
     */
    @Override
    public void eraseAll() throws EmptyDrawableException {
        if (coloredPolygons.isEmpty()){
            throw new EmptyDrawableException("there is already nothing to draw");
        }
        coloredPolygons.clear();
    }

    /**
     * returns all polygons which are drawn
     * @return collection of drawn polygons
     */
    @Override
    public Collection<ColoredPolygon> getAllDrawnPolygons() {
        return coloredPolygons;
    }

    /**
     * finds all polygons which has same vertex as its declared
     * @param vertex vertex to be searched for
     * @return collection of all polygons with same vertex
     */
    @Override
    public Collection<ColoredPolygon> findPolygonsWithVertex(Vertex2D vertex) {
        List<ColoredPolygon> polygonsWithSameVertex = new ArrayList<>();
        for (ColoredPolygon polygon : coloredPolygons){
                for (Vertex2D vertexOfPoint : polygon.getPolygon().getVertices()){
                    if (vertexOfPoint.equals(vertex)){
                        polygonsWithSameVertex.add(polygon);
                    }
                }
            }
        return polygonsWithSameVertex;
    }

    /**
     * trying to create polygon
     * vertices list cannot be null or cannot have less than 3 vertices
     * trying to return new collection of polygons, if there is some null, tries to remove it and continue if its not
     * still correct, throws MVE
     * @param vertices collection which the polygon can be built from
     * @return collection of vertices
     * @throws MissingVerticesException when there is not enough vertices
     */
    @Override
    public Polygon tryToCreatePolygon(List<Vertex2D> vertices) throws MissingVerticesException {
        if (vertices==null){
            throw new NullPointerException("parameter is null");
        }
        if (vertices.size()<3){
            throw new MissingVerticesException("polygon has lest than 3 vertices");
        }
        List<Vertex2D> copyOfVertices = new ArrayList<>();
        copyOfVertices.addAll(vertices);
        while (true){
            try {
                Vertex2D[] arrayOfVertices = copyOfVertices.toArray(new Vertex2D[copyOfVertices.size()]);
                return new CollectionPolygon(arrayOfVertices);
            } catch (IllegalArgumentException e) {
                if (removeFirstNull(copyOfVertices).size() < 3) {
                    throw new MissingVerticesException("not enough vertices", e);
                }

            }
        }

    }

    /**
     * private method used for removing the first null
     * @param vertices list of vertices
     * @return vertices without one null
     */
    private List<Vertex2D> removeFirstNull(List<Vertex2D> vertices){
        vertices.remove(null);
        return vertices;
    }

    /**
     * using the iterator to be able to go through all lists
     * while there is another list
     * trying to draw polygons if there is something to draw changes boolean to true
     * if nothing is drawn and there is no more list, throws EDE
     * @param collectionPolygons collection of polygons (every polygon is collection of vertices)
     * @throws EmptyDrawableException if there is nothing to drwa
     */
    @Override
    public void tryToDrawPolygons(List<List<Vertex2D>> collectionPolygons) throws EmptyDrawableException {
        Iterator<List<Vertex2D>> iterator = collectionPolygons.iterator();
        boolean atLeastOneDrawn = false;

        while (iterator.hasNext()){
            List<Vertex2D> vertices = iterator.next();
            try {
                Polygon p = tryToCreatePolygon(vertices);
                drawPolygon(p);
                atLeastOneDrawn = true;
            } catch (MissingVerticesException | NullPointerException | TransparentColorException ex) {
                if (!iterator.hasNext() && !atLeastOneDrawn) {
                    throw new EmptyDrawableException("Nothing to draw", ex);
                }
            }
        }
    }


    /**
     * gets polygons with same color
     * filter all polygons which color is same as color we want
     * gets all polygons
     * set them to list
     * @param color we want
     * @return list of same colored polygons
     */
    public Collection<Polygon> getPolygonsWithColor(Color color){
        return coloredPolygons.stream()
                .filter (x -> x.getColor() == color)
                .map(ColoredPolygon::getPolygon)
                .collect(Collectors.toList());
    }

}
