package cz.muni.fi.pb162.project.exceptions;

/**
 * class representing exception when polygon is missing some vertices
 */
public class MissingVerticesException extends Exception {
    /**
     * constructor where is message of problem and the cause
     * @param message problem
     */
    public MissingVerticesException(String message) {
        super(message);
    }
    /**
     * constructor where is message of problem and the cause
     * @param message problem
     * @param cause cause
     */
    public MissingVerticesException(String message, Throwable cause) {
        super(message, cause);
    }
}
