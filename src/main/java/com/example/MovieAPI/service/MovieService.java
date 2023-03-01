package com.example.MovieAPI.service;

import com.example.MovieAPI.dto.MovieDTO;
import com.example.MovieAPI.mapper.MovieDtoMapper;
import com.example.MovieAPI.mapper.MovieDtoMapperImplementation;
import com.example.MovieAPI.model.Movie;
import com.example.MovieAPI.repositories.CharacterRepository;
import com.example.MovieAPI.repositories.FranchiseRepository;
import com.example.MovieAPI.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final FranchiseRepository franchiseRepository;
    private final CharacterRepository characterRepository;
    private final MovieDtoMapperImplementation movieDtoMapperImplementation;

    public MovieService(MovieRepository movieRepository, FranchiseRepository franchiseRepository, CharacterRepository characterRepository, MovieDtoMapperImplementation movieDtoMapperImplementation) {
        this.movieRepository = movieRepository;
        this.franchiseRepository = franchiseRepository;
        this.characterRepository = characterRepository;
        this.movieDtoMapperImplementation = movieDtoMapperImplementation;
    }


    public MovieDTO findById(Integer integer) {
        if(integer <= 0) return null;

        Optional<Movie> movieOptional = movieRepository.findById(integer);
        Movie movie = movieOptional.get();

        return movie != null ? movieDtoMapperImplementation.movieToMovieDto(movie) : null;
    }


    public List<MovieDTO> findAll() {

        List<Movie> movies = movieRepository.findAll();
        List<MovieDTO> movieDTOList = new ArrayList<>();

        for(Movie movie : movies){
            movieDTOList.add(movieDtoMapperImplementation.movieToMovieDto(movie));
        }

        return movieDTOList;
    }


    public MovieDTO add(MovieDTO movieDTO) {
        if (movieDTO == null) return null;
        Movie movie = movieRepository.save(movieDtoMapperImplementation.movieDtoToMovie(movieDTO));
        return movie != null ? movieDtoMapperImplementation.movieToMovieDto(movie):null;
    }


    public MovieDTO update(MovieDTO movieDTO) {
        if (movieDTO == null) return null;
        Movie movie = movieRepository.save(movieDtoMapperImplementation.movieDtoToMovie(movieDTO));
        return movie != null ? movieDtoMapperImplementation.movieToMovieDto(movie):null;
    }


    public int deleteById(Integer integer) {
        return movieRepository.deleteByMovieId(integer);
    }


    public void delete(Movie entity) {
        movieRepository.delete(entity);
    }


    public MovieDTO setFranchiseForMovie(int movieId, int franchiseId) {

        Movie movie;
        if(movieId <= 0) {} else {
            movie = movieRepository.findById(movieId).get();
            movie.setFranchise(franchiseRepository.findById(franchiseId).get());
            update(movieDtoMapperImplementation.movieToMovieDto(movie));
            return movieDtoMapperImplementation.movieToMovieDto(movie);
        }
        return null;

    }


    public MovieDTO updateCharacterMovieList(int movieId, int characterId) {

        Movie movie;
        MovieDTO movieDTO;
        if(!(movieId <= 0)) {
            movie = movieRepository.findById(movieId).get();
            movieDTO = movieDtoMapperImplementation.movieToMovieDto(movie);
            movieDTO.getCharacterList().add(characterId);
            return update(movieDtoMapperImplementation.movieToMovieDto(movieDtoMapperImplementation.movieDtoToMovie(movieDTO)));
        }
        return null;
    }


    public MovieDTO getMovieByTitle(String name) {

        if (name== null || name.isEmpty()) return null;
        Movie movie = movieRepository.findMovieByMovieTitle(name);
        MovieDTO movieDTO = movieDtoMapperImplementation.movieToMovieDto(movie);
        return movieDTO;
    }

}
