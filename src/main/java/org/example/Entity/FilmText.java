package org.example.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

@Data
@Entity
@Table(schema = "Movies", name = "film_text")
public class FilmText {
    @Id
    @Column(name = "film_id")
    private Short id;

    @OneToOne()
    @JoinColumn(name = "film_id")
    private Film film;

    private String title;

    @Column(columnDefinition = "text")
    @Type(type = "text")
    private String description;
}
