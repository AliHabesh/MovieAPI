package com.example.MovieAPI.services;

import com.example.MovieAPI.model.Movie;

public interface MovieService extends CrudService<Movie, Integer>{
    void setFranchiseForMovie(int movieId, int franchiseId);
    void setCharacterForMovie(int movieId, int characterId);

    Movie getMovieByTitle(String name);
}
