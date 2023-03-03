package com.example.MovieAPI.controller;

import com.example.MovieAPI.dto.MovieDTO;
import com.example.MovieAPI.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all movies")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Movies found successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDTO.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No movies found",
                    content = @Content)
    })
    @GetMapping// GET: localhost:8080/api/v1/movie
    public ResponseEntity<Collection<MovieDTO>> getAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @Operation(summary = "Get movie with id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Movie with provided id found successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDTO.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No movie found with provided id",
                    content = @Content)
    })
    @GetMapping("{id}") // GET: localhost:8080/api/v1/movie/1
    public ResponseEntity<MovieDTO> path(@PathVariable int id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    @Operation(summary = "Add or update movie")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Movie successfully added/updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong",
                    content = @Content)
    })
    @PutMapping("/save") // PUT: localhost:8080/api/v1/movie/save
    public ResponseEntity save(@RequestBody MovieDTO movie) {
        movieService.update(movie);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update movie character list with one character")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Character added for movie successfully",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong",
                    content = @Content)
    })
    @PutMapping("{movieId}/character/{characterId}") // PUT: localhost:8080/api/v1/movie/1/character/2
    public ResponseEntity updateMovieCharacterList(@PathVariable int movieId, @PathVariable int characterId) {
        movieService.updateCharacterMovieList(movieId, characterId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update movie character list with list of characters")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Characters added for movie successfully",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong",
                    content = @Content)
    })
    @PutMapping("{movieId}/characters") // PUT: localhost:8080/api/v1/movie/1/characters
    public ResponseEntity updateMovieCharactersListWithList(@RequestBody List<Integer> list, @PathVariable int movieId) {
        movieService.updateCharacterMovieListWithList(movieId, list);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update/add franchise for single movie")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Franchise updated/added successfully",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong",
                    content = @Content)
    })
    @PutMapping("{movieId}/franchise/{franchiseId}") // PUT: localhost:8080/api/v1/movie/1/franchise/2
    public ResponseEntity updateFranchise(@PathVariable int movieId, @PathVariable int franchiseId) {
        movieService.setFranchiseForMovie(movieId, franchiseId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update/add franchise for list of movies")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "franchise updated/added for movies successfully",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong",
                    content = @Content)
    })
    @PutMapping("{movieId}/franchise") // PUT: localhost:8080/api/v1/movie/1/franchise
    public ResponseEntity updateMovieFranchiseWithList(@RequestBody List<Integer> list, @PathVariable int movieId) {
        movieService.setFranchiseForMovieWithList(list, movieId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete movie")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Movie successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong",
                    content = @Content)
    })
    @DeleteMapping("delete/{id}") // DELETE: localhost:8080/api/v1/movie/delete/1
    public ResponseEntity delete(@PathVariable int id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Search movie with movie title")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Movie successfully added/updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDTO.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong",
                    content = @Content)
    })
    @GetMapping("search") // GET: localhost:8080/api/v1/movie/search?name=Iron Man
    public ResponseEntity<MovieDTO> findByName(@RequestParam String name) {
        return ResponseEntity.ok(movieService.getMovieByTitle(name));
    }

}