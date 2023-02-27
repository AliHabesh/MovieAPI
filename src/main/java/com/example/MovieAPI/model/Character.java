package com.example.MovieAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int characterId;
    private String fullName;
    private String alias;
    private String gender;
    private String picture;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "movie_character",
            joinColumns = @JoinColumn(name = "characterId"),
            inverseJoinColumns = @JoinColumn(name = "movieId"))
    private List<Movie> movies;


    void printMovies(){
     movies.forEach(movie -> System.out.println(movie));
 }
}
