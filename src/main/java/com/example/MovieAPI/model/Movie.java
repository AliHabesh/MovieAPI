package com.example.MovieAPI.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;
    private String movieTitle;
    private String genre;
    private String picture;

    private String director;
    private String movieReleaseYear;

    private String trailer;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "movie_character",
            joinColumns = @JoinColumn(name = "movieId"),
            inverseJoinColumns = @JoinColumn(name = "characterId"))
    private List<Character> characterList;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "franchiseId")
    private Franchise franchise;

  public void printCharacterList(){
      this.characterList.forEach(value -> System.out.println(value));
  }
}
