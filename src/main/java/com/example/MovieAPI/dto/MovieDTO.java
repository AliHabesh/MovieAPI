package com.example.MovieAPI.dto;

import com.example.MovieAPI.model.Character;
import com.example.MovieAPI.model.Franchise;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private int movieId;
    private String movieTitle;
    private String genre;
    private String picture;
    private String movieReleaseYear;
    private List<Character> characterList;

    private Franchise franchise;
}
