package com.example.MovieAPI.mapper;

import com.example.MovieAPI.dto.FranchiseDTO;
import com.example.MovieAPI.model.Franchise;
import com.example.MovieAPI.model.Movie;
import com.example.MovieAPI.repositories.FranchiseRepository;
import com.example.MovieAPI.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FranchiseDtoMapperImplementation implements FranchiseDtoMapper{

    @Autowired
    private MovieRepository movieRepository;
    public FranchiseDTO franchiseToFranchiseDto(Franchise franchise) {
        if (franchise == null) {
            return null;
        } else {
            FranchiseDTO franchiseDTO = new FranchiseDTO();
            franchiseDTO.setFranchiseId(franchise.getFranchiseId());
            franchiseDTO.setName(franchise.getName());
            franchiseDTO.setDescription(franchise.getDescription());
            List<Movie> list = franchise.getMovies();
            if (list != null) {
                franchiseDTO.setMovies(convertToIntegerList(list));
            }

            return franchiseDTO;
        }
    }

    private List<Integer> convertToIntegerList(List<Movie> movieList){
        ArrayList<Integer> list = new ArrayList<>();
        for (Movie i: movieList){
            list.add(i.getMovieId());
        }
        return list;
    }

    public Franchise franchiseDtoToFranchise(FranchiseDTO franchiseDTO) {
        if (franchiseDTO == null) {
            return null;
        } else {
            Franchise franchise = new Franchise();
            franchise.setFranchiseId(franchiseDTO.getFranchiseId());
            franchise.setName(franchiseDTO.getName());
            franchise.setDescription(franchiseDTO.getDescription());
            List<Integer> list = franchiseDTO.getMovies();
            if (list != null) {
                franchise.setMovies(convertToMovieList(list));
            }

            return franchise;
        }
    }

    private List<Movie> convertToMovieList(List<Integer> movieList){
        ArrayList<Movie> list = new ArrayList<>();

        for (Integer i: movieList){
            Movie movie = movieRepository.findById(i).get();
            list.add(movie);
        }
        return list;
    }
}
