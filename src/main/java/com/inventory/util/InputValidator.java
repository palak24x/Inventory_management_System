package com.inventory.util;

public class InputValidator {

    public static int positiveInt(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Enter a value greater than 0.");
        }
        return value;
    }

    private InputValidator() {
        // Utility class - no object needed.
    }
}
