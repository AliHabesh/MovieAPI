package com.example.MovieAPI.controller;

import com.example.MovieAPI.dto.MovieDTO;
import com.example.MovieAPI.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
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
            @ApiResponse(responseCode = "200",
                    description = "Movies found successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDTO.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "No movies found",
                    content = @Content)
    })
    @GetMapping// GET: localhost:8080/api/v1/movie
    public ResponseEntity<Collection<MovieDTO>> getAllMovies() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @Operation(summary = "Get movie with id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Movie with provided id found successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDTO.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "No movie found with provided id",
                    content = @Content)
    })
    @GetMapping("{id}") // GET: localhost:8080/api/v1/movie/1
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable int id) {
        MovieDTO movieDTO = movieService.findById(id);
        if(movieDTO == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(movieDTO);
    }

    @Operation(summary = "Add or update movie")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Movie successfully added/updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDTO.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong with adding/updating movie",
                    content = @Content)
    })
    @PostMapping("/save") // PUT: localhost:8080/api/v1/movie/save
    public ResponseEntity saveMovie(@RequestBody MovieDTO movie) {
        return ResponseEntity.ok(movieService.update(movie));
    }

    @Operation(summary = "Update movie character list with one character")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Character added for movie successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDTO.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Couldn't find movie with provided id or character with provided id",
                    content = @Content)
    })
    @PutMapping("{movieId}/character/{characterId}") // PUT: localhost:8080/api/v1/movie/1/character/2
    public ResponseEntity updateMovieCharacterList(@PathVariable int movieId, @PathVariable int characterId) {
        MovieDTO movieDTO = movieService.updateCharacterMovieList(movieId, characterId);
        if(movieDTO != null) {
            return ResponseEntity.ok(movieDTO);
        } else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Update movie character list with list of characters")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Characters added for movie successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDTO.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Couldn't find movie with provided id or character with provided id",
                    content = @Content)
    })
    @PutMapping("{movieId}/characters") // PUT: localhost:8080/api/v1/movie/1/characters
    public ResponseEntity updateMovieCharactersListWithList(@RequestBody List<Integer> list, @PathVariable int movieId) {
        MovieDTO movieDTO = movieService.updateCharacterMovieListWithList(movieId, list);
        if(movieDTO != null) return ResponseEntity.ok(movieDTO);
        else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Update/add franchise for single movie")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Franchise updated/added successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDTO.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Couldn't find movie with provided id or franchise with provided id",
                    content = @Content)
    })
    @PutMapping("{movieId}/franchise/{franchiseId}") // PUT: localhost:8080/api/v1/movie/1/franchise/2
    public ResponseEntity updateFranchiseForMovie(@PathVariable int movieId, @PathVariable int franchiseId) {
        MovieDTO movieDTO = movieService.setFranchiseForMovie(movieId, franchiseId);
        if(movieDTO != null) return ResponseEntity.ok(movieDTO);
        else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Update/add franchise for list of movies")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "franchise updated/added for movies successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Integer.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Couldn't find movie with provided id or franchise with provided id",
                    content = @Content)
    })
    @PutMapping("{franchiseId}/franchise") // PUT: localhost:8080/api/v1/movie/1/franchise
    public ResponseEntity updateMovieFranchiseWithList(@RequestBody List<Integer> list, @PathVariable int franchiseId) {
        int result = movieService.setFranchiseForMovieWithList(list, franchiseId);
        if(result != 1) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete movie")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Movie successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Movie with provided id didn't exist",
                    content = @Content)
    })
    @DeleteMapping("delete/{id}") // DELETE: localhost:8080/api/v1/movie/delete/1
    public ResponseEntity deleteMovie(@PathVariable int id) {
        int x = movieService.deleteById(id);
        if(x != 1) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Search movie with movie title")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Movie successfully added/updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDTO.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Movie with provided title didn't exist",
                    content = @Content)
    })
    @GetMapping("search") // GET: localhost:8080/api/v1/movie/search?title=Iron Man
    public ResponseEntity<MovieDTO> getMovieByTitle(@RequestParam String title) {
        MovieDTO movieDTO = movieService.getMovieByTitle(title);
        if(movieDTO != null) {
            return ResponseEntity.ok(movieService.getMovieByTitle(title));
        }
        else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}