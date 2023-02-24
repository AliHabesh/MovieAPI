package com.example.MovieAPI.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

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
}
