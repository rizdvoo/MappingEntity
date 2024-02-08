package org.example.Entity;

import org.example.Entity.Converters.FilmRatingConverter;
import org.example.Entity.Converters.YearConverter;
import org.example.Entity.Enums.*;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import 	java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;


@Data
@Entity
@Table(schema = "Movies", name = "film")
public class Film {
    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    private String title;

    @Column(columnDefinition = "text")
    @Type(type = "text")
    private String description;

    @Column(name = "release_year", columnDefinition = "year")
    @Convert(converter = YearConverter.class)
    private Year releaseYear;

    @ManyToOne()
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private Language originalLanguageId;
    
    @Column(name = "rental_duration")
    private Byte rentalDuration;

    @Column(name = "rental_rate")
    private BigDecimal rentalRate;

    private Short length;

    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;

    @Column(columnDefinition = "enum('G', 'PG', 'PG-13', 'R', 'NC-17')")
    @Convert(converter = FilmRatingConverter.class)
    private FilmRating rating;

    @Column(name = "special_features", columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    private String specialFeatures;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="film_category",
    joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    private List<Category> categories;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "film_actor",
    joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
    inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"))
    private List<Actor> actors;

    public Set<FilmFeatures> getSpecialFeatures() {
        if (isNull(this.specialFeatures) || this.specialFeatures.isEmpty()) {
            return null;
        }

        Set<FilmFeatures> result = new HashSet<>();
        String[] split = specialFeatures.split(",");
        for (String s : split) {
            result.add(FilmFeatures.getFutureByValue(s));
        }
        result.remove(null);
        return result;
    }

    public void setSpecialFeatures(Set<FilmFeatures> specialFeatures) {
        if (isNull(specialFeatures)) {
            this.specialFeatures = null;
        }
        this.specialFeatures = specialFeatures.stream().map(FilmFeatures::getValue).collect(Collectors.joining(","));
    }
}
