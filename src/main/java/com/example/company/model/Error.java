package com.example.company.model;

public enum Error {
//    public static final String data_is_null = "data is null";

    DATA_IS_NULL("{0} is null"),
    ;

    private final String message;

    /**
     * Constructor for the enum.
     * @param message message string
     */
    Error(String message) {
        this.message = message;
    }

    /**
     * Get the message string
     * @return the message string
     */
    public String getMessage() {
        return message;
    }
}
