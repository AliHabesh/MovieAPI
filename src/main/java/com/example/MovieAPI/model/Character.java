package com.example.MovieAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "movieId")
    private List<Movie> movie;
}
