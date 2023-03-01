package com.example.MovieAPI.dto;

import com.example.MovieAPI.model.Character;
import com.example.MovieAPI.model.Franchise;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private int movieId;
    private String movieTitle;
    private String director;
    private String genre;
    private String picture;
    private String movieReleaseYear;
    private String trailer;
    private List<Integer> characterList;

    private Franchise franchise;


}
