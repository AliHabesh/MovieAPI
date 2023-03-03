package com.example.MovieAPI.service;

import com.example.MovieAPI.dto.MovieDTO;
import com.example.MovieAPI.mapper.MovieDtoMapperImplementation;
import com.example.MovieAPI.model.Franchise;
import com.example.MovieAPI.model.Movie;
import com.example.MovieAPI.model.Character;
import com.example.MovieAPI.repositories.CharacterRepository;
import com.example.MovieAPI.repositories.FranchiseRepository;
import com.example.MovieAPI.repositories.MovieRepository;
import jakarta.transaction.Transactional;
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
        if(movieOptional.isPresent()) {
            Movie movie = movieOptional.get();
            return movieDtoMapperImplementation.movieToMovieDto(movie);
        }
        return null;
    }

    public List<MovieDTO> findAll() {

        List<Movie> movies = movieRepository.findAll();
        List<MovieDTO> movieDTOList = new ArrayList<>();

        for(Movie movie : movies){
            movieDTOList.add(movieDtoMapperImplementation.movieToMovieDto(movie));
        }

        return movieDTOList;
    }

    public MovieDTO update(MovieDTO movieDTO) {
        if (movieDTO == null) return null;
        Movie movie = movieRepository.save(movieDtoMapperImplementation.movieDtoToMovie(movieDTO));
        return movie != null ? movieDtoMapperImplementation.movieToMovieDto(movie):null;
    }

    @Transactional
    public int deleteById(Integer integer) {
        Movie movie;
        List<Character> characters;
        if(movieRepository.findById(integer).isPresent()) {
            movie = movieRepository.findById(integer).get();
            characters = movie.getCharacterList();
            for(Character index : characters) {
                index.getMovies().subList(index.getMovies().indexOf(movie), 1);
                characterRepository.save(index);
            }
            movie.getCharacterList().clear();
            movieRepository.save(movie);
            movieRepository.deleteByMovieId(integer);
            return 1;
        }
        return -1;
    }

    public MovieDTO setFranchiseForMovie(int movieId, int franchiseId) {
        Movie movie;
        if(!(movieId <= 0) && !(franchiseId <= 0)) {
            if(movieRepository.findById(movieId).isPresent() && franchiseRepository.findById(franchiseId).isPresent()) {
                movie = movieRepository.findById(movieId).get();
                movie.setFranchise(franchiseRepository.findById(franchiseId).get());
                update(movieDtoMapperImplementation.movieToMovieDto(movie));
                return movieDtoMapperImplementation.movieToMovieDto(movie);
            }
        }
        return null;
    }

    public int setFranchiseForMovieWithList(List<Integer> list, int franchiseId) { //returns 1 = success, returns 0 = fail
        Movie movie;
        Franchise franchise;
        if(!(franchiseId <= 0)) {
            if(franchiseRepository.findById(franchiseId).isPresent()) {
            franchise = franchiseRepository.findById(franchiseId).get();
            for(int i : list){
                if(movieRepository.findById(i).isPresent()) {
                    movie = movieRepository.findById(i).get();
                    movie.setFranchise(franchise);
                    update(movieDtoMapperImplementation.movieToMovieDto(movie));
                }
            }
            return 1;
        }}
        return -1;
    }


    public MovieDTO updateCharacterMovieList(int movieId, int characterId) {
        MovieDTO movieDTO;
        if((movieId > 0) && (characterId > 0)) {
            if(movieRepository.findById(movieId).isPresent() && characterRepository.findById(characterId).isPresent()) {
                movieDTO = movieDtoMapperImplementation.movieToMovieDto(movieRepository.findById(movieId).get());
                if(!movieDTO.getCharacterList().contains(characterId)) {
                    movieDTO.getCharacterList().add(characterId);
                }
                return update(movieDTO);
            }
        }
        return null;
    }

    public MovieDTO updateCharacterMovieListWithList(int movieId, List<Integer> characterIdList) {
        Movie movie;
        MovieDTO movieDTO;
        if(!(movieId <= 0)) {
            if(movieRepository.findById(movieId).isPresent()){
                movie = movieRepository.findById(movieId).get();
                movieDTO = movieDtoMapperImplementation.movieToMovieDto(movie);
                for(int i : characterIdList) {
                    if(!movieDTO.getCharacterList().contains(i) && characterRepository.findById(i).isPresent()){
                        movieDTO.getCharacterList().add(i);
                        update(movieDTO);
                    }
                }
                return movieDTO;
            }
        }
        return null;
    }


    public MovieDTO getMovieByTitle(String name) {
        MovieDTO movieDTO = null;
        if (name== null || name.isEmpty()) return null;
        if(movieRepository.findMovieByMovieTitle(name).isPresent()) {
            Movie movie = movieRepository.findMovieByMovieTitle(name).get();
            movieDTO = movieDtoMapperImplementation.movieToMovieDto(movie);
        }
        return movieDTO;
    }
}
