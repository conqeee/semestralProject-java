package cz.muni.fi.pb162.project.geometry;

/**
 * class represents regular octagon
 */
public class RegularOctagon extends GeneralRegularPolygon {

    /**
     * number of edge is 8 because of being octagon
     * @param middle middle point
     * @param radius circumcircle octagon
     */
    public RegularOctagon(Vertex2D middle, double radius){
        super(middle,8,radius);
    }
}
