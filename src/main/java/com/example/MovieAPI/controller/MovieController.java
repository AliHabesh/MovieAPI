package com.example.MovieAPI.controller;

import com.example.MovieAPI.dto.MovieDTO;
import com.example.MovieAPI.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

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

    @PutMapping("/save") // PUT: localhost:8080/api/v1/movie/save
    public ResponseEntity save(@RequestBody MovieDTO movie) {
        // Validates if body is correct
        movieService.update(movie);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{movieId}/character/{characterId}") // PUT: localhost:8080/api/v1/movie/1/character/2
    public ResponseEntity updateMovieCharacterList(@PathVariable int movieId, @PathVariable int characterId) {
        movieService.updateCharacterMovieList(movieId, characterId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{movieId}/characters") // PUT: localhost:8080/api/v1/movie/1/characters
    public ResponseEntity updateMovieCharactersListWithList(@RequestBody List<Integer> list, @PathVariable int movieId) {
        movieService.updateCharacterMovieListWithList(movieId, list);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}/franchise/{franchiseId}") // PUT: localhost:8080/api/v1/movie/1/franchise/2
    public ResponseEntity updateFranchise(@PathVariable int id, @PathVariable int franchiseId) {
        movieService.setFranchiseForMovie(id, franchiseId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{franchiseId}/franchise") // PUT: localhost:8080/api/v1/movie/1/franchise
    public ResponseEntity updateMovieFranchiseWithList(@RequestBody List<Integer> list, @PathVariable int franchiseId) {
        movieService.setFranchiseForMovieWithList(list, franchiseId);
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