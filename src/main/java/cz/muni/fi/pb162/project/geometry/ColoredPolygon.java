package cz.muni.fi.pb162.project.geometry;

/**
 * class represents array of polygons and their color
 */
public class ColoredPolygon {
    private Polygon polygon;
    private Color color;

    /**
     * constructor for creating the coloredpolynom
     * @param polygon the array of vertices
     * @param color color of paths
     */
    public ColoredPolygon(Polygon polygon, Color color){
        this.polygon = polygon;
        this.color = color;
    }

    /**
     * returns the polygon
     * @return polygon
     */
    public Polygon getPolygon() {
            return polygon;
        }

    /**
     * getting the color
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * equals function comparing the vertices
     * @param o second object
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColoredPolygon polygon1 = (ColoredPolygon) o;

        return polygon != null ? polygon.equals(polygon1.polygon) : polygon1.polygon == null;
    }

    /**
     * hash of coloredpolynom
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return polygon != null ? polygon.hashCode() : 0;
    }
}
