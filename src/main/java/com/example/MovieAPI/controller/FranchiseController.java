package com.example.MovieAPI.controller;

import com.example.MovieAPI.dto.CharacterDTO;
import com.example.MovieAPI.dto.FranchiseDTO;
import com.example.MovieAPI.model.Character;
import com.example.MovieAPI.service.CharacterService;
import com.example.MovieAPI.service.FranchiseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/franchises")
public class FranchiseController {

    private FranchiseService franchiseService;

    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }

    //Tested, it works
    @GetMapping
    public List<FranchiseDTO> getAllFranchises(){
        return franchiseService.getAllFranchises();
    }

    //Tested, it works
    @GetMapping("/byId/{id}")
    public FranchiseDTO getFranchiseById(@PathVariable("id") int id){
        return franchiseService.getFranchiseById(id);
    }

    @GetMapping("/byId/{id}/characters")
    public List<Integer> getAllCharactersInAFranchise(@PathVariable("id") int id){
        return franchiseService.getAllCharactersInFranchise(id);
    }

    //Tested, it works
    @GetMapping("/byName/{name}")
    public FranchiseDTO getFranchiseByName(@PathVariable("name") String name){
        return franchiseService.getFranchiseByName(name);
    }


    //Tested, it works
    @PostMapping("/save")
    public FranchiseDTO addFranchise(@RequestBody FranchiseDTO franchiseDTO){
        return franchiseService.saveFranchise(franchiseDTO);
    }

    //Tested, it works
    /*
    @GetMapping("/update/{franchiseId}/movies/{id}")
    public FranchiseDTO addMovieToFranchise(@PathVariable("franchiseId") int franchiseId, @PathVariable("id") int movieId){
        return franchiseService.addMovieToFranchise(franchiseId, movieId);
    }

     */


    //Tested, it works
    @DeleteMapping("/delete/{id}")
    public int deleteFranchiseById(@PathVariable("id") int id){
        return franchiseService.deleteFranchiseById(id);
    }
}
