package com.epam.glasshouse.exception;

public class GlasshouseException extends Exception {

    public GlasshouseException() {
    }

    public GlasshouseException(String message) {
        super(message);
    }

    public GlasshouseException(String message, Throwable cause) {
        super(message, cause);
    }

    public GlasshouseException(Throwable cause) {
        super(cause);
    }
}
