package com.example.MovieAPI.service;

import com.example.MovieAPI.dto.CharacterDTO;
import com.example.MovieAPI.dto.FranchiseDTO;
import com.example.MovieAPI.dto.MovieDTO;
import com.example.MovieAPI.mapper.CharacterDtoMapper;
import com.example.MovieAPI.mapper.FranchiseDtoMapper;
import com.example.MovieAPI.model.Character;
import com.example.MovieAPI.model.Franchise;
import com.example.MovieAPI.repositories.CharacterRepository;
import com.example.MovieAPI.repositories.FranchiseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class FranchiseService {


    private FranchiseRepository franchiseRepository;

    public FranchiseService(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }



        public FranchiseDTO saveFranchise(FranchiseDTO franchiseDTO){
            if (franchiseDTO == null) return null;
            Franchise franchise = franchiseRepository.save(FranchiseDtoMapper.INSTANCE.franchiseDtoToFranchise(franchiseDTO));
            return franchise != null ? FranchiseDtoMapper.INSTANCE.franchiseToFranchiseDto(franchise):null;
        }

        public FranchiseDTO getFranchiseByName(String name){
            if (name == null || name.isEmpty()) return null;

            Franchise franchise = franchiseRepository.findByName(name);
            return franchise != null ? FranchiseDtoMapper.INSTANCE.franchiseToFranchiseDto(franchise):null;
        }

        public FranchiseDTO getFranchiseById(int id){
            if (id <= 0) return null;

            Optional<Franchise> franchiseOptional = franchiseRepository.findById(id);
            Franchise franchise = franchiseOptional.get();

            return franchise != null ? FranchiseDtoMapper.INSTANCE.franchiseToFranchiseDto(franchise):null;
        }

        public List<FranchiseDTO> getAllFranchises(){
            List<Franchise> franchises = franchiseRepository.findAll();
            List<FranchiseDTO> franchiseDTOList = convertListFranchiseToDto(franchises);
            return franchiseDTOList != null ? franchiseDTOList: null;
        }

        private List<FranchiseDTO> convertListFranchiseToDto(List<Franchise> franchises){
            if (franchises == null || franchises.isEmpty())
                return null;

            ArrayList<FranchiseDTO> franchiseDTOArrayList = new ArrayList<>();
            franchises.forEach(value -> franchiseDTOArrayList.add(FranchiseDtoMapper.INSTANCE.franchiseToFranchiseDto(value)));
            return franchiseDTOArrayList;
        }

        public FranchiseDTO addMovieToFranchise(MovieDTO movieDTO, String characterName){
            FranchiseDTO franchiseDTO = FranchiseDtoMapper.INSTANCE.franchiseToFranchiseDto(franchiseRepository.findByName(characterName));
            return null;
        }

        public int deleteFranchiseById(Integer id){
            franchiseRepository.deleteById(id);
            return 1;
        }
}
