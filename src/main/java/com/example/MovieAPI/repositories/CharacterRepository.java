package com.example.MovieAPI.repositories;

import com.example.MovieAPI.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CharacterRepository extends JpaRepository<Character, Integer> {


}
