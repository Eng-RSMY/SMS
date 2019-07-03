package com.dream.utils;

/**
 * The Status Enum class
 *
 * @author Dileep
 * @version 1.0
 * Date 20/05/2019.
 */
public enum Status {
    ACTIVE("ACTIVE"), PASSIVE("PASSIVE");
    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
