package com.example.MovieAPI.services;

import com.example.MovieAPI.model.Movie;
import com.example.MovieAPI.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie findById(Integer integer) {
        if(integer <= 0) return null;
        return this.movieRepository.findById(integer).get();
    }

    @Override
    public Collection<Movie> findAll() {
        return this.movieRepository.findAll();
    }

    @Override
    public Movie add(Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public Movie update(Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        movieRepository.deleteById(integer);
    }

    @Override
    public void delete(Movie entity) {
        movieRepository.delete(entity);
    }

    @Override
    public void setFranchiseForMovie(int movieId, int franchiseId) {
    /*
        Movie movie;
        if(movieId <= 0) {} else {
            movie = movieRepository.findById(movieId).get();
            movie.setFranchise(franchiseRespository.findFranchiseById(franchiseId));

            update(movie);
        }

     */

    }

    @Override
    public void setCharacterForMovie(int movieId, int characterId) {
    /*


        Movie movie;
        if(!(movieId <= 0)) {
            movie = movieRepository.findById(movieId).get();
            movie.getCharacterList().add(characterRepository.getCharacterById(characterId));

            update(movie);
        }
    */
    }

    @Override
    public Movie getMovieByTitle(String name) {
        System.out.println(movieRepository.findMovieByMovieTitle(name));
        return movieRepository.findMovieByMovieTitle(name);
    }
}
