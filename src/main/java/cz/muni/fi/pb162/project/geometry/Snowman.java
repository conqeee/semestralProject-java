package cz.muni.fi.pb162.project.geometry;

/**
 * class represents circles - snowman
 */
public class Snowman {
    private static final double DEFAULT_CONST = 0.8;
    private static final int NUMBER_OF_CIRCLES = 3;
    private RegularPolygon[] polygons=new GeneralRegularPolygon[NUMBER_OF_CIRCLES];

    /**
     * setting decreasing factor to default if the imput is lower/equal to 0 or higher than 1
     * creating the field of circles
     * @param regularPolygon the first octagon
     * @param decreasingFactor says what number will multiply radius
     */
    public Snowman(RegularPolygon regularPolygon, double decreasingFactor){
        if(decreasingFactor <=0 || decreasingFactor > 1){
            decreasingFactor = DEFAULT_CONST;
        }
        polygons[0] = regularPolygon;
        for(int i = 1; i < NUMBER_OF_CIRCLES; i++){
            polygons[i]=new GeneralRegularPolygon(polygons[i-1].getCenter().
                    move(newCenter(i-1,decreasingFactor)),
                    regularPolygon.getNumEdges(),
                    polygons[i-1].getRadius()*decreasingFactor);
        }
    }

    /**
     * creating a new middle of another circle
     * @param index number declaring the position in array
     * @param decreasingFactor radius multiplyer
     * @return new Vertex2D point - center of next circle
     */
    private Vertex2D newCenter(int index,double decreasingFactor){
        return new Vertex2D(0,polygons[index].getRadius()+polygons[index].getRadius()*decreasingFactor);
    }

    /**
     * getting field of octagons
     * @return field of octagons
     */
    public RegularPolygon[] getBalls() {
        return polygons;
    }
}
