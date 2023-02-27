package com.example.MovieAPI.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public int getMovieId() {
        return movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getGenre() {
        return genre;
    }

    public String getPicture() {
        return picture;
    }

    public String getDirector() {
        return director;
    }

    public String getMovieReleaseYear() {
        return movieReleaseYear;
    }

    public String getTrailer() {
        return trailer;
    }

    public List<Character> getCharacterList() {
        return characterList;
    }

    public Franchise getFranchise() {
        return franchise;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setMovieReleaseYear(String movieReleaseYear) {
        this.movieReleaseYear = movieReleaseYear;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public void setCharacterList(List<Character> characterList) {
        this.characterList = characterList;
    }

    public void setFranchise(Franchise franchise) {
        this.franchise = franchise;
    }
}
