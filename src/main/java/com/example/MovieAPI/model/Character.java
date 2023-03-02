package com.example.MovieAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int characterId;
    private String fullName;
    private String alias;
    private String gender;
    private String picture;

    public Character(String fullName, String alias, String gender, String picture) {
        this.fullName = fullName;
        this.alias = alias;
        this.gender = gender;
        this.picture = picture;
    }

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movie_character",
            joinColumns = @JoinColumn(name = "characterId"),
            inverseJoinColumns = @JoinColumn(name = "movieId"))
    private List<Movie> movies;


    public void printMovies(){
     movies.forEach(movie -> System.out.println(movie));
    }
}
