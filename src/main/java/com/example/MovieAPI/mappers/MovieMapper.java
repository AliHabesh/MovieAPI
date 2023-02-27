package com.example.MovieAPI.mappers;

import com.example.MovieAPI.dto.MovieDTO;
import com.example.MovieAPI.model.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    default MovieDTO toMovieDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId(movie.getMovieId());
        movieDTO.setDirector(movie.getDirector());
        movieDTO.setGenre(movie.getGenre());
        movieDTO.setMovieReleaseYear(movie.getMovieReleaseYear());
        movieDTO.setMovieTitle(movie.getMovieTitle());
        movieDTO.setPicture(movie.getPicture());
        movieDTO.setTrailer(movie.getTrailer());
        movieDTO.setFranchise(movie.getFranchise().getFranchiseId());

        return movieDTO;
    };
    default Movie toMovie(MovieDTO dto) {
        Movie movie = new Movie();
        movie.setMovieId(dto.getMovieId());
        movie.setDirector(dto.getDirector());
        movie.setGenre(dto.getGenre());
        movie.setMovieReleaseYear(dto.getMovieReleaseYear());
        movie.setMovieTitle(dto.getMovieTitle());
        movie.setPicture(dto.getPicture());
        movie.setTrailer(dto.getTrailer());

        return movie;
    };
}
