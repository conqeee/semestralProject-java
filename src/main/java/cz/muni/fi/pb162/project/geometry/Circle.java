package cz.muni.fi.pb162.project.geometry;

/**
 * class Circle
 */
public class Circle extends GeneralRegularPolygon implements Measurable, Circumcircle {
    /**
     * circle Constructor
     * main color is RED
     * @param center middle of the circle
     * @param radius radius
     */
    public Circle(Vertex2D center, double radius){
        super(center, Integer.MAX_VALUE,radius);
        setColor(Color.RED);
    }

    /**
     * empty constructor
     * calling upper one with middle [0,0] and radius 1
     */
    public Circle(){
        /*this.center = new Vertex2D(0,0);
        this.radius = 1.0;*/
        this(new Vertex2D(0,0),1);

    }

    @Override
    public double getEdgeLength() {
        return super.getEdgeLength();
    }

    @Override
    public String toString() {
        return ("Circle: center="+getCenter()+", radius="+getRadius());
    }

    /*@Override
    public double getWidth() {
        return (radius*2);
    }

    @Override
    public double getHeight() {
        return (radius*2);
    }*/

}
