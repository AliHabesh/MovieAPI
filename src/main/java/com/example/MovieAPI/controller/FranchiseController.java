package com.example.MovieAPI.controller;

import com.example.MovieAPI.dto.CharacterDTO;
import com.example.MovieAPI.dto.FranchiseDTO;
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

    @GetMapping
    public List<FranchiseDTO> getAllCharacters(){
        return franchiseService.getAllFranchises();
    }

    @GetMapping("/byId/{id}")
    public FranchiseDTO getCharacterById(@PathVariable("id") int id){
        return franchiseService.getFranchiseById(id);
    }

    @GetMapping("/byName/{name}")
    public FranchiseDTO getCharacterById(@PathVariable("name") String name){
        return franchiseService.getFranchiseByName(name);
    }

    @PostMapping("/save")
    public FranchiseDTO saveCharacter(@RequestBody FranchiseDTO franchiseDTO){
        return franchiseService.saveFranchise(franchiseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public int deleteFranchiseById(@PathVariable("id") int id){
        return franchiseService.deleteFranchiseById(id);
    }
}
