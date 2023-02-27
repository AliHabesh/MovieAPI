package com.example.MovieAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;
    private String movieTitle;
    private String genre;
    private String picture;

    private String director;
    private String movieReleaseYear;

    private String trailer;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_character",
            joinColumns = @JoinColumn(name = "movieId"),
            inverseJoinColumns = @JoinColumn(name = "characterId"))
    private List<Character> characterList;

    @ManyToOne
    @JoinColumn(name = "franchiseId")
    private Franchise franchise;

}
