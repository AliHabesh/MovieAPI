package com.example.MovieAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Character {
    private int characterId;
    private String fullName;
    private String characterAlias;
    private String gender;
    private String picture;

    @ManyToMany
    private List<Movie> movies;
}
