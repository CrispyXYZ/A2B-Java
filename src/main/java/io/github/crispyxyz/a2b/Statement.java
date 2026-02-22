package io.github.crispyxyz.a2b;

import module java.base;

public record Statement(String left, String right) {
    public Statement(String[] tokens) {
        if (tokens.length != 2) {
            throw new IllegalArgumentException("Invalid argument count");
        }
        this(tokens[0], tokens[1]);
    }
}
