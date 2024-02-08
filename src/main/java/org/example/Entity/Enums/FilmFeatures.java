package org.example.Entity.Enums;

import static java.util.Objects.isNull;

public enum FilmFeatures {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");
    private final String value;

    FilmFeatures(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FilmFeatures getFutureByValue(String value) {
        if (isNull(value) || value.isEmpty()) {
            return null;
        }

        for (FilmFeatures filmFeatures : FilmFeatures.values()) {
            if (filmFeatures.getValue().equals(value)) {
                return filmFeatures;
            }
        }

        return null;
    }
}
