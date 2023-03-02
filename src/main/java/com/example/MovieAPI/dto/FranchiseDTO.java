package com.example.MovieAPI.dto;

import com.example.MovieAPI.model.Movie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FranchiseDTO {

    private int franchiseId;
    private String name;
    private String description;
    private List<Integer> movies;
}
