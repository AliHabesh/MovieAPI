package com.example.MovieAPI.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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


}
