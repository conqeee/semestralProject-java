package cz.muni.fi.pb162.project.geometry;

/**
 * class represents all regular polygons
 * implements methods of regular polynom and colored
 */
public class GeneralRegularPolygon implements RegularPolygon, Colored {
    private Vertex2D center;
    private int edges;
    private double radius;
    private double edgeLenght;
    private Color color;

    /**
     * constructor
     */
    public GeneralRegularPolygon(){

    }

    /**
     * constructor for regular polygon
     * sets the basic color - black
     * @param center center of polygon
     * @param edges number of edges
     * @param radius radius of circumcircle
     */
    public GeneralRegularPolygon(Vertex2D center, int edges, double radius){
        this.center=center;
        this.edges=edges;
        this.radius=radius;
        this.color = Color.BLACK;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int getNumEdges() {
        return edges;
    }

    /**
     * if edges = 0 (its circle) returns edge =0
     * else counts size of edge
     * @return edge
     */
    @Override
    public double getEdgeLength() {
        if (getNumEdges() == 0) return 0;
        return (2 * getRadius() * Math.sin(Math.PI / getNumEdges()));
    }

    @Override
    public Vertex2D getCenter() {
        return center;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public double getWidth() {
        return radius*2;
    }

    @Override
    public double getHeight() {
        return radius*2;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getNumEdges());
        sb.append("-gon: center=").append(center);
        sb.append(", radius=").append(radius);
        sb.append(", color=").append(getColor());
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeneralRegularPolygon that = (GeneralRegularPolygon) o;

        if (edges != that.edges) return false;
        if (Double.compare(that.radius, radius) != 0) return false;
        if (Double.compare(that.edgeLenght, edgeLenght) != 0) return false;
        if (center != null ? !center.equals(that.center) : that.center != null) return false;
        return color == that.color;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = center != null ? center.hashCode() : 0;
        result = 31 * result + edges;
        temp = Double.doubleToLongBits(radius);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(edgeLenght);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
