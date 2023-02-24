package com.example.MovieAPI.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private int movieId;
    private String name;
    private String genre;
    private Date releaseYear;
    private String director;
    private String picture;
    private String trailer;

    @ManyToMany
    private List<Character> characters;

    @OneToMany(mappedBy = "franchise_id")
    private Franchise franchiseId;
}
