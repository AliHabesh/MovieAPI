package com.example.MovieAPI.dto;

import com.example.MovieAPI.model.Movie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterDTO {
    private int characterId;
    private String fullName;
    private String alias;
    private String gender;
    private String picture;
    private List<Integer> movie;
}
