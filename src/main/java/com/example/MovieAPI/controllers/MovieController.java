package com.example.MovieAPI.controllers;

import com.example.MovieAPI.dto.MovieDTO;
import com.example.MovieAPI.mappers.MovieMapper;
import com.example.MovieAPI.model.Movie;
import com.example.MovieAPI.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/movie") // Base URL
public class MovieController {

    private final MovieService movieService;

    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @GetMapping// GET: localhost:8080/api/v1/movie
    public ResponseEntity<Collection<MovieDTO>> getAll() {

        Collection<MovieDTO> movieDTOS = new ArrayList<>();
        Collection<Movie> movies = movieService.findAll();
        for(Movie movie : movies){
            movieDTOS.add(movieMapper.toMovieDTO(movie));
        }

        return ResponseEntity.ok(movieDTOS);
    }

    @GetMapping("{id}") // GET: localhost:8080/api/v1/test/1
    public ResponseEntity<MovieDTO> path(@PathVariable int id) {
        MovieDTO movieDTO = movieMapper.toMovieDTO(movieService.findById(id));
        return ResponseEntity.ok(movieDTO);
    }

    @PutMapping("{id}") // PUT: localhost:8080/api/v1/movie/1
    public ResponseEntity update(@RequestBody MovieDTO movie, @PathVariable int id) {
        // Validates if body is correct
        if(id != movie.getMovieId())
            return ResponseEntity.badRequest().build();
        movieService.update(movieMapper.toMovie(movie));
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/movie/1
    public ResponseEntity delete(@PathVariable int id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("search") // GET: localhost:8080/api/v1/movie/search?name=Iron Man
    public ResponseEntity<MovieDTO> findByName(@RequestParam String name) {
        MovieDTO movieDTO = movieMapper.toMovieDTO(movieService.getMovieByTitle(name));
        return ResponseEntity.ok(movieDTO);
    }

}
