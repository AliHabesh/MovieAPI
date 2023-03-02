package com.example.MovieAPI.service;


import com.example.MovieAPI.dto.FranchiseDTO;
import com.example.MovieAPI.mapper.FranchiseDtoMapperImplementation;
import com.example.MovieAPI.model.Franchise;
import com.example.MovieAPI.model.Movie;
import com.example.MovieAPI.repositories.FranchiseRepository;
import com.example.MovieAPI.repositories.MovieRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class FranchiseService {



    private FranchiseDtoMapperImplementation franchiseDtoMapperImplementation;
    private FranchiseRepository franchiseRepository;
    private MovieRepository movieRepository;

    public FranchiseService(FranchiseDtoMapperImplementation franchiseDtoMapperImplementation, FranchiseRepository franchiseRepository, MovieRepository movieRepository) {
        this.franchiseDtoMapperImplementation = franchiseDtoMapperImplementation;
        this.franchiseRepository = franchiseRepository;
        this.movieRepository = movieRepository;
    }

    public FranchiseDTO saveFranchise(FranchiseDTO franchiseDTO){
            if (franchiseDTO == null) return null;
            Franchise franchise = franchiseRepository.save(franchiseDtoMapperImplementation.franchiseDtoToFranchise(franchiseDTO));
            return franchise != null ? franchiseDtoMapperImplementation.franchiseToFranchiseDto(franchise):null;
        }

        public FranchiseDTO getFranchiseByName(String name){
            if (name == null || name.isEmpty()) return null;

            Franchise franchise = franchiseRepository.findByName(name);
            return franchise != null ? franchiseDtoMapperImplementation.franchiseToFranchiseDto(franchise):null;
        }

        public FranchiseDTO getFranchiseById(int id){
            if (id <= 0) return null;

            Optional<Franchise> franchiseOptional = franchiseRepository.findById(id);
            Franchise franchise = franchiseOptional.get();

            return franchise != null ? franchiseDtoMapperImplementation.franchiseToFranchiseDto(franchise):null;
        }

        public List<FranchiseDTO> getAllFranchises(){
            List<Franchise> franchises = franchiseRepository.findAll();
            List<FranchiseDTO> franchiseDTOList = convertListFranchiseToDto(franchises);
            return franchiseDTOList != null ? franchiseDTOList : null;
        }

        private List<FranchiseDTO> convertListFranchiseToDto(List<Franchise> franchises){
            if (franchises == null || franchises.isEmpty())
                return null;

            ArrayList<FranchiseDTO> franchiseDTOArrayList = new ArrayList<>();
            franchises.forEach(value -> franchiseDTOArrayList.add(franchiseDtoMapperImplementation.franchiseToFranchiseDto(value)));
            return franchiseDTOArrayList;
        }

        /*
        public FranchiseDTO addMovieToFranchise(int franchiseId, int movieId){
            FranchiseDTO franchiseDTO = franchiseDtoMapperImplementation.franchiseToFranchiseDto(franchiseRepository.findById(franchiseId).get());
            Movie movie = movieRepository.findById(movieId).get();
            movie.setFranchise(franchiseDtoMapperImplementation.franchiseDtoToFranchise(franchiseDTO));
            franchiseDTO.getMovies().add(movie.getMovieId());
            franchiseRepository.save(franchiseDtoMapperImplementation.franchiseDtoToFranchise(franchiseDTO));
            movieRepository.save(movie);
            return franchiseDTO;
        }

         */

        public int deleteFranchiseById(Integer id){
            franchiseRepository.deleteById(id);
            return 1;
        }

        public List<Integer> getAllCharactersInFranchise(int franchiseId){
            Franchise franchise = franchiseRepository.findById(franchiseId).get();
            ArrayList<Integer> characterList = new ArrayList<>();
            franchise.getMovies().forEach(movie -> {
                movie.getCharacterList().forEach(character -> {
                    characterList.add(character.getCharacterId());
                });
            });
            return characterList;
        }
}
