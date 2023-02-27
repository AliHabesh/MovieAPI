package com.example.MovieAPI.services;

import com.example.MovieAPI.model.Movie;

public interface MovieService extends CrudService<Movie, Integer>{
    void setFranchise(int movieId, int franchiseId);
    void setCharacter(int movieId, int characterId);
}
