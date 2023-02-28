package com.example.MovieAPI;

import com.example.MovieAPI.model.Character;
import com.example.MovieAPI.model.Movie;
import com.example.MovieAPI.repositories.CharacterRepository;
import com.example.MovieAPI.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootApplication
public class MovieApiApplication implements ApplicationRunner {
	@Autowired
	CharacterRepository characterRepository;

	@Autowired
	MovieRepository movieRepository;

	public static void main(String[] args) {
		SpringApplication.run(MovieApiApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

	}
}
