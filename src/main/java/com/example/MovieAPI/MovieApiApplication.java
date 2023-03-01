package com.example.MovieAPI;

import com.example.MovieAPI.dto.CharacterDTO;
import com.example.MovieAPI.dto.FranchiseDTO;
import com.example.MovieAPI.dto.MovieDTO;
import com.example.MovieAPI.mapper.CharacterDtoMapper;
import com.example.MovieAPI.mapper.FranchiseDtoMapper;
import com.example.MovieAPI.model.Character;
import com.example.MovieAPI.model.Franchise;
import com.example.MovieAPI.repositories.CharacterRepository;
import com.example.MovieAPI.repositories.FranchiseRepository;
import com.example.MovieAPI.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class MovieApiApplication implements ApplicationRunner {
	@Autowired
	CharacterRepository characterRepository;

	@Autowired
	FranchiseRepository franchiseRepository;

	@Autowired
	MovieRepository movieRepository;



	public static void main(String[] args) {
		SpringApplication.run(MovieApiApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		/*
		Character character = characterRepository.findByFullName("Tony Stark");
		CharacterDTO characterDTO = CharacterDtoMapper.INSTANCE.characterToCharacterDto(character);
		System.out.println(characterDTO);

		Optional<Franchise> franchise = franchiseRepository.findById(1);
		FranchiseDTO franchiseDTO = FranchiseDtoMapper.INSTANCE.franchiseToFranchiseDto(franchise.get());
		System.out.println(franchiseDTO);

		//Franchise franchise = franchiseRepository.findByName("Marvel Cinematic Universe");
		List<Franchise> franchise1 = franchiseRepository.findAll();
		System.out.println(franchise1);
		//System.out.println(FranchiseDtoMapper.INSTANCE.franchiseToFranchiseDto(franchise));
		 */
	}
}
