package cz.muni.fi.pb162.project.geometry;

/**
 * @author Miroslav Trzil
 */

/**
 * Vertex2D represents the point by coordinates x,y
 */
public class Vertex2D implements Comparable<Vertex2D>{

    private final double x;
    private final double y;

    /**
     * constructor
     */
    public Vertex2D() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * constructor
     * @param x is coordinate x
     * @param y is coordinate y
     */
    public Vertex2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    Vertex2D a = new Vertex2D(4,5);

    /**
     * sums up coordinate x + y of one point
     * @return x+y of specific point
     */
    double sumCoordinates() {
        return this.x + this.y;
    }

    /**
     * moves the point by summing up with another vertices
     * @param vertex - vertices
     * @return new Vertex point created by counting [x1+x2,y1+y2]
     */
    public Vertex2D move(Vertex2D vertex) {
        return new Vertex2D(x + vertex.getX(),y + vertex.getY());
    }

    public double getX(){
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * getting string of another types
     * @return basic string model of point
     */
    public String toString() {
        return "["+x+", "+y+"]";
    }



    public double seeX(){
        return this.x;
    }

    /**
     * getting lenght of triangle's side from some Vertex2D point to another one
     * @param vertex - second point of side
     * @return d - the distance
     */
    double distance(Vertex2D vertex){
        if (vertex==null) {
            return -1.0;
        }
        double d = Math.sqrt(Math.pow(vertex.x - this.x, 2.0) + Math.pow(vertex.y - this.y, 2.0));
        return d;

    }


    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * some vertices are equals when their X and Y is the same
     * @param pointB second vertex
     * @return true or false
     */
    public boolean equals(Object pointB){
        if (pointB instanceof Vertex2D){
            return this.getX() ==  ((Vertex2D) pointB).getX() && this.getY() == ((Vertex2D) pointB).getY();
        }
        return false;
    }

    /**
     * comparing two vertex object
     * @param o another vertex
     * @return + or - or 0 int in case of comparism
     */
    @Override
    public int compareTo(Vertex2D o) {
        if(this.x==o.x){
            return (Double.compare(this.y,o.y));
        }
        return (Double.compare(this.x,o.x));
    }

    //double.compare

}
