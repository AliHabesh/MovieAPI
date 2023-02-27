package com.example.MovieAPI.services;

import com.example.MovieAPI.model.Movie;
import com.example.MovieAPI.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie findById(Integer integer) {
        return this.movieRepository.findById(integer).get();
    }

    @Override
    public Collection<Movie> findAll() {
        return this.movieRepository.findAll();
    }

    @Override
    public Movie add(Movie entity) {
        return null;
    }

    @Override
    public Movie update(Movie entity) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Movie entity) {

    }

    @Override
    public void setFranchise(int movieId, int franchiseId) {

    }

    @Override
    public void setCharacter(int movieId, int characterId) {

    }
}
