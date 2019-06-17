package cz.muni.fi.pb162.project.geometry;

/**
 * class represents Square
 * implements methods of interface Circumcircle
 */
public class Square extends GeneralRegularPolygon implements Circumcircle {
    private Vertex2D pointA;

    /**
     * Constructor uses param Circumcircle,
     * than it calls general regular octagon class using middle - center point of circumcircle
     * 4 edges as a square has and a radius of circumcircle
     * counting edge and the point A
     * @param circumcircle Circumcircle object
     */
    public Square(Circumcircle circumcircle){
        super(circumcircle.getCenter(),4,circumcircle.getRadius());
        double halfEdge=getEdgeLength()/2;
        pointA = circumcircle.getCenter().move(new Vertex2D(-halfEdge,-halfEdge));
    }

    /**
     * returns the Vertex2D point of the square
     * @param index declares what point it returns
     * @return left bottom, right bottom, right top, left top in case of index
     */
    public Vertex2D getVertex(int index){
        if(index==0){
            return pointA;
        } else if (index==1){
            return pointA.move(new Vertex2D(getEdgeLength(),0));
        } else if (index==2){
            return pointA.move(new Vertex2D(getEdgeLength(),getEdgeLength()));
        } else if (index==3){
            return pointA.move(new Vertex2D(0,getEdgeLength()));
        }
        return null;
    }

    @Override
    public String toString() {
        return ("Square: vertices="+getVertex(0)+" "+getVertex(1)+" "+getVertex(2)+" "+getVertex(3));
    }
}
