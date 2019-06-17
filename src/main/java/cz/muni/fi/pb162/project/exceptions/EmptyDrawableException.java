package cz.muni.fi.pb162.project.exceptions;

/**
 * class represents exception where on paper is nothing to drwa
 */
public class EmptyDrawableException extends Exception{
    /**
     * constructor where is message of problem
     * @param message problem
     */
    public EmptyDrawableException(String message) {
        super(message);
    }

    /**
     * constructor where is message of problem and the cause
     * @param message problem
     * @param cause cause
     */
    public EmptyDrawableException(String message, Throwable cause) {
        super(message, cause);
    }
}

