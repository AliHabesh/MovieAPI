package com.example.MovieAPI.repositories;

import com.example.MovieAPI.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {

    Optional<Character> findById(Integer id);
    Character findByFullName(String fullName);


    Character save(Character character);

    void deleteById(Integer id);



}
