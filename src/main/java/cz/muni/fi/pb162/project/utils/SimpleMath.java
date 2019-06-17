package cz.muni.fi.pb162.project.utils;

import cz.muni.fi.pb162.project.geometry.Vertex2D;

/**
 * class for simple math operation - getting lowest,highest points
 */
public class SimpleMath {
    /**
     * returns the point with biggest X vertice
     * @param vertices array of Vertex2D points
     * @return Vertex2D point - biggestX
     */
    public static Vertex2D maxX(Vertex2D[] vertices) {
        Vertex2D biggestX = vertices[0];
        for(int i=0; i < vertices.length; i++){
            if(biggestX.getX()<vertices[i].getX()){
                biggestX=vertices[i];
            }
        }
         return biggestX;
    }

    /**
     * returns the point with lowest X vertice
     * @param vertices array of Vertex2D points
     * @return Vertex2D point - smallestX
     */
    public static Vertex2D minX(Vertex2D[] vertices){
        Vertex2D smallestX = vertices[0];
        for(int i=0; i < vertices.length; i++){
            if(smallestX.getX()>vertices[i].getX()){
                smallestX=vertices[i];
            }
        }
        return smallestX;
    }

    /**
     * returns the point with biggest Y vertice
     * @param vertices array of Vertex2D points
     * @return Vertex2D point - biggestY
     */
    public static Vertex2D maxY(Vertex2D[] vertices){
        Vertex2D biggestY = vertices[0];
        for(int i=0; i<vertices.length; i++){
            if(biggestY.getY()<vertices[i].getY()){
                biggestY=vertices[i];
            }
        }
        return biggestY;
    }

    /**
     * returns the point with lowest Y vertice
     * @param vertices array of Vertex2D points
     * @return Vertex2D point - lowestY
     */
    public static Vertex2D minY(Vertex2D[] vertices){
        Vertex2D smallestY = vertices[0];
        for(int i=0; i<vertices.length; i++){
            if(smallestY.getY()>vertices[i].getY()){
                smallestY=vertices[i];
            }
        }
        return smallestY;
    }
}
