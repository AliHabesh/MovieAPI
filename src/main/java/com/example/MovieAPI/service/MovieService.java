package com.example.MovieAPI.service;

import com.example.MovieAPI.dto.MovieDTO;
import com.example.MovieAPI.mapper.MovieDtoMapper;
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

    public MovieService(MovieRepository movieRepository, FranchiseRepository franchiseRepository, CharacterRepository characterRepository) {
        this.movieRepository = movieRepository;
        this.franchiseRepository = franchiseRepository;
        this.characterRepository = characterRepository;
    }


    public MovieDTO findById(Integer integer) {
        if(integer <= 0) return null;

        Optional<Movie> movieOptional = movieRepository.findById(integer);
        Movie movie = movieOptional.get();

        return movie != null ? MovieDtoMapper.INSTANCE.movieToMovieDto(movie) : null;
    }


    public List<MovieDTO> findAll() {

        List<Movie> movies = movieRepository.findAll();
        List<MovieDTO> movieDTOList = new ArrayList<>();

        for(Movie movie : movies){
            movieDTOList.add(MovieDtoMapper.INSTANCE.movieToMovieDto(movie));
        }

        return movieDTOList;
    }


    public MovieDTO add(MovieDTO movieDTO) {
        if (movieDTO == null) return null;
        Movie movie = movieRepository.save(MovieDtoMapper.INSTANCE.movieDtoToMovie(movieDTO));
        return movie != null ? MovieDtoMapper.INSTANCE.movieToMovieDto(movie):null;
    }


    public MovieDTO update(MovieDTO movieDTO) {
        if (movieDTO == null) return null;
        Movie movie = movieRepository.save(MovieDtoMapper.INSTANCE.movieDtoToMovie(movieDTO));
        return movie != null ? MovieDtoMapper.INSTANCE.movieToMovieDto(movie):null;
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
            update(MovieDtoMapper.INSTANCE.movieToMovieDto(movie));
            return MovieDtoMapper.INSTANCE.movieToMovieDto(movie);
        }
        return null;

    }


    public MovieDTO setCharacterForMovie(int movieId, int characterId) {

        Movie movie;
        if(!(movieId <= 0)) {
            movie = movieRepository.findById(movieId).get();
            movie.getCharacterList().add(characterRepository.findById(characterId).get());
            update(MovieDtoMapper.INSTANCE.movieToMovieDto(movie));
            return MovieDtoMapper.INSTANCE.movieToMovieDto(movie);
        }
        return null;
    }


    public MovieDTO getMovieByTitle(String name) {

        if (name== null || name.isEmpty()) return null;
        Movie movie = movieRepository.findMovieByMovieTitle(name);
        MovieDTO movieDTO = MovieDtoMapper.INSTANCE.movieToMovieDto(movie);
        movieDTO.setCharacterList(movie.getCharacterList());
        return movieDTO;
    }

}
