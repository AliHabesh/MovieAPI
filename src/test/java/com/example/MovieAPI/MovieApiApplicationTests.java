package com.example.MovieAPI;

import com.example.MovieAPI.repositories.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MovieApiApplicationTests {
	@Autowired
	MovieRepository movieRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void loadCharacterByName(){

	}

}
