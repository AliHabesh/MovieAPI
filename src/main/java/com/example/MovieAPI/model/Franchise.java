package com.example.MovieAPI.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Franchise {
    private int franchiseId;
    private String name;
    private String description;
}
