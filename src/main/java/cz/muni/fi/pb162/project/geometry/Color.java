package cz.muni.fi.pb162.project.geometry;

/**
 * enum type named Color represents different colors
 */
public enum Color {
    BLACK,
    RED,
    BLUE,
    YELLOW,
    ORANGE,
    GREEN,
    WHITE;

    /**
     * to string
     * @return color written with lower cases
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }


}
