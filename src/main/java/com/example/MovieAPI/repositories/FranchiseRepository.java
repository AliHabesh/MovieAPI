package com.example.MovieAPI.repositories;

import com.example.MovieAPI.model.Character;
import com.example.MovieAPI.model.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Integer> {
    Optional<Franchise> findById(Integer id);
    Franchise findByName(String name);


    Franchise save(Character character);

    void deleteById(Integer id);
}
