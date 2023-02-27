package com.example.MovieAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "franchise", fetch = FetchType.EAGER)
    private List<Movie> movies;
}
