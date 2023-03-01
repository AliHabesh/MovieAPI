package com.example.MovieAPI.mapper;

import com.example.MovieAPI.dto.MovieDTO;
import com.example.MovieAPI.model.Character;
import com.example.MovieAPI.model.Movie;
import com.example.MovieAPI.repositories.CharacterRepository;
import com.example.MovieAPI.repositories.FranchiseRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieDtoMapperImplementation implements MovieDtoMapper {

    private final CharacterRepository characterRepository;
    private final FranchiseRepository franchiseRepository;

    public MovieDtoMapperImplementation(CharacterRepository characterRepository, FranchiseRepository franchiseRepository) {
        this.characterRepository = characterRepository;
        this.franchiseRepository = franchiseRepository;
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
        if(mov.getFranchise() != null) movieDTO.setFranchise(mov.getFranchise().getFranchiseId());

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
        if(franchiseRepository.findById(movieDTO.getFranchise()).isPresent()){
            movie.setFranchise(franchiseRepository.findById(movieDTO.getFranchise()).get());
        }


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
            if(characterRepository.findById(i).isPresent())
                returnList.add(characterRepository.findById(i).get());
        }
        return returnList;
    }
}
