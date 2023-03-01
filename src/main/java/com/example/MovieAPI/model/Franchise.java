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
public class Franchise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int franchiseId;
    private String name;
    private String description;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "franchise", fetch = FetchType.EAGER)
    private List<Movie> movies;

    void printMovies(){
        movies.forEach(movie -> System.out.println(movie));
    }



}
