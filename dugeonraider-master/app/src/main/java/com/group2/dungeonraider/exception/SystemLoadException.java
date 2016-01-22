package com.group2.dungeonraider.exception;

/**
 * Created by Rohit on 10/27/2015.
 */
public class SystemLoadException extends Exception{

    private String message = null;

    public SystemLoadException() {
        super();
    }

    public SystemLoadException(String message) {
        super(message);
        this.message = message;
    }

    public SystemLoadException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
