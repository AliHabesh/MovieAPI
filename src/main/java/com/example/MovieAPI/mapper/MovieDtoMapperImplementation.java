package com.example.MovieAPI.mapper;

import com.example.MovieAPI.dto.MovieDTO;
import com.example.MovieAPI.model.Character;
import com.example.MovieAPI.model.Movie;
import com.example.MovieAPI.repositories.CharacterRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieDtoMapperImplementation implements MovieDtoMapper {

    private final CharacterRepository characterRepository;

    public MovieDtoMapperImplementation(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public MovieDTO movieToMovieDto(Movie mov) {
        if (mov == null) {
            return null;
        }

        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setMovieId(mov.getMovieId());
        movieDTO.setMovieTitle(mov.getMovieTitle());
        movieDTO.setDirector(mov.getDirector());
        movieDTO.setGenre(mov.getGenre());
        movieDTO.setPicture(mov.getPicture());
        movieDTO.setMovieReleaseYear(mov.getMovieReleaseYear());
        movieDTO.setTrailer(mov.getTrailer());
        List<Character> list = mov.getCharacterList();
        if (list != null) {
            movieDTO.setCharacterList(convertCharacterList(list));
        }
        movieDTO.setFranchise(mov.getFranchise());

        return movieDTO;
    }

    @Override
    public Movie movieDtoToMovie(MovieDTO movieDTO) {
        if (movieDTO == null) {
            return null;
        }

        Movie movie = new Movie();

        movie.setMovieId(movieDTO.getMovieId());
        movie.setMovieTitle(movieDTO.getMovieTitle());
        movie.setGenre(movieDTO.getGenre());
        movie.setPicture(movieDTO.getPicture());
        movie.setDirector(movieDTO.getDirector());
        movie.setMovieReleaseYear(movieDTO.getMovieReleaseYear());
        movie.setTrailer(movieDTO.getTrailer());
        List<Integer> list = movieDTO.getCharacterList();
        if (list != null) {
            movie.setCharacterList(convertIntegerList(list));
        }
        movie.setFranchise(movieDTO.getFranchise());

        return movie;
    }

    private List<Integer> convertCharacterList(List<Character> list) {
        List<Integer> returnList = new ArrayList<>();
        for(Character i : list){
            returnList.add(i.getCharacterId());
        }
        return returnList;
    }

    private List<Character> convertIntegerList(List<Integer> list) {
        List<Character> returnList = new ArrayList<>();
        for(int i : list){
            returnList.add(characterRepository.findById(i).get());
        }
        return returnList;
    }
}
