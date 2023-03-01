package com.example.MovieAPI.controller;

import com.example.MovieAPI.dto.MovieDTO;
import com.example.MovieAPI.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/movie") // Base URL
public class MovieController {


    private final MovieService movieService;


    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping// GET: localhost:8080/api/v1/movie
    public ResponseEntity<Collection<MovieDTO>> getAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("{id}") // GET: localhost:8080/api/v1/movie/1
    public ResponseEntity<MovieDTO> path(@PathVariable int id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    @PutMapping("{id}") // PUT: localhost:8080/api/v1/movie/1
    public ResponseEntity update(@RequestBody MovieDTO movie, @PathVariable int id) {
        System.out.println("hello");
        // Validates if body is correct
        if(id != movie.getMovieId())
            return ResponseEntity.badRequest().build();
        movieService.update(movie);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}/characters/{characterId}") // PUT: localhost:8080/api/v1/movie/1/characters/2
    public ResponseEntity update(@RequestBody MovieDTO movie, @PathVariable int id, @PathVariable int characterId) {
        // Validates if body is correct
        System.out.println("hello character");
        if(id != movie.getMovieId())
            return ResponseEntity.badRequest().build();
        movieService.updateCharacterMovieList(id, characterId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/movie/1
    public ResponseEntity delete(@PathVariable int id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("search") // GET: localhost:8080/api/v1/movie/search?name=Iron Man
    public ResponseEntity<MovieDTO> findByName(@RequestParam String name) {
        return ResponseEntity.ok(movieService.getMovieByTitle(name));
    }

}
