package com.example.MovieAPI.repositories;

import com.example.MovieAPI.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Optional<Movie> findById(Integer integer);

    Optional<Movie> findMovieByMovieTitle(String movieTitle);

    List<Movie> findAll();

    Movie save(Movie movie);

    void delete(Movie entity);

    int deleteByMovieId(int id);


}
