package org.example.Entity.Converters;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.Entity.Enums.FilmRating;

@Converter(autoApply = true)
public class FilmRatingConverter implements AttributeConverter<FilmRating, String> {
    @Override
    public String convertToDatabaseColumn(FilmRating filmRating) {
        return filmRating.getValue();
    }

    @Override
    public FilmRating convertToEntityAttribute(String s) {
        for (FilmRating rating : FilmRating.values()) {
            if (rating.getValue().equals(s)) {
                return rating;
            }
        }
        return null;
    }
}
