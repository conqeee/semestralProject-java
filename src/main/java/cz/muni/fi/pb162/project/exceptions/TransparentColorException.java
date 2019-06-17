package cz.muni.fi.pb162.project.exceptions;

/**
 * class represents exception where the color is white
 */
public class TransparentColorException extends Exception {
    /**
     * constructor where is message of problem and the cause
     * @param message problem
     */
    public TransparentColorException(String message) {
        super(message);
    }

    /**
     * constructor where is message of problem and the cause
     * @param message problem
     * @param cause cause
     */
    public TransparentColorException(String message, Throwable cause) {
        super(message, cause);
    }
}
