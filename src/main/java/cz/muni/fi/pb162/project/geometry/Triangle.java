package cz.muni.fi.pb162.project.geometry;

/**
 * class Triangle
 */
public class Triangle extends ArrayPolygon implements Measurable{
    private final Triangle[] subTriangle = new Triangle[3];
    public static final double EPSILON = 0.001;

    /**
     * Triangle constructor - contains Vertex2D info about 3 vertices of triangle
     * putting vertices of each point to Vertex[] array using a ArrayPolygon class constructor
     * @param point1 point A
     * @param point2 point B
     * @param point3 point C
     */
    public Triangle(Vertex2D point1, Vertex2D point2,Vertex2D point3){
        super(new Vertex2D[]{point1,point2,point3});
    }

    /**
     * getting vertex from Vertex2D array
     * @param index the number of specific point of triangle
     * @return the vertices of specific point if its in range of triangle (3 points)
     */

    /**
     * method which says if the triangle was already divided, if there is no subtriangle, it returns false, if not true
     * @return true or false
     */
    public boolean isDivided(){
        return !(subTriangle[0] == null);
    }

    /**
     * getting subtriangle's vertices if there are (if statement secures nondivided triangle or out of range indexes)
     * @param index of subTriangle we want to know info of
     * @return vertices of vertices of subTriangle with index
     */
    public Triangle getSubTriangle(int index) {
        if (!isDivided() || index < 0 || index > 2) {
            return null;
        }
        return subTriangle[index];
    }

    /**
     * calculating middle of medges
     * @param vertex1 one coordinate
     * @param vertex2 second coordinate
     * @return new Vertex2D - new point
     */
    private Vertex2D calcMiddle(Vertex2D vertex1, Vertex2D vertex2 ) {
        double middleX = (vertex1.getX()+vertex2.getX())/2;
        double middleY = (vertex1.getY()+vertex2.getY())/2;
        return new Vertex2D(middleX,middleY);
    }

    /**
     * dividing triangle to subtriangles
     * if its already divided, return false
     * if not
     * setting middles of edges - X, Y, Z are the middle points
     * continuing with setting points of each subTriangle using Triangle class,
     * than returns true
     * @return true or false
     */
    public boolean divide(){
        if (isDivided()){
            return false;
        }
        Vertex2D a = calcMiddle(getVertex(0), getVertex(1));
        Vertex2D b = calcMiddle(getVertex(1), getVertex(2));
        Vertex2D c = calcMiddle(getVertex(0), getVertex(2));

        subTriangle[0] = new Triangle(getVertex(0),a,c);
        subTriangle[1] = new Triangle(a,b,getVertex(1));
        subTriangle[2] = new Triangle(getVertex(2),c,b);
        return true;
    }

    /**
     * getting true or false of comparing sides of triangle (distance from A to B compared with B to C etc.)
     * EPSILON 0.001 - next iteration = psf EPSILON = 0.001
     * @return true if its equilateral false if its not
     */
    boolean isEquilateral(){
        Vertex2D v0 = getVertex(0);
        Vertex2D v1 = getVertex(1);
        Vertex2D v2= getVertex(2);
        return ((Math.abs(v0.distance(v1)-v1.distance(v2)) < EPSILON) &&
                (Math.abs(v1.distance(v2)-v2.distance(v0)) < EPSILON));

    }

    /**
     * overloaded method - depth causes the number of repetitions
     * if depth is out of range - 0 means all dividing had been done, less than 0 is not valid number - returns false
     * than it divides the starting triangle
     * than it calls for each triangle of subTriangle array divide overloaded method divide, just with depth -1
     * @param depth how many divides the program has to do
     * @return true or false
     */
    public boolean divide(int depth){
        if (depth<=0){
            return false;
        }
        divide();
        for (Triangle triangle : subTriangle) {
            triangle.divide(depth-1);
        }
        return true;
    }
}

