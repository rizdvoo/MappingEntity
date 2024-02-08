package org.example.Entity.Enums;

public enum FilmRating {
    G("G"),
    PG("PG"),
    PG13("PG-13"),
    R("R"),
    NC17("NC-17");

    private final String value;

    FilmRating(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
