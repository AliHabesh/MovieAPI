package com.example.MovieAPI.controller;

import com.example.MovieAPI.dto.CharacterDTO;
import com.example.MovieAPI.service.CharacterService;
import jakarta.websocket.server.PathParam;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/characters")
public class CharacterController {

    private CharacterService characterService;


    public CharacterController(CharacterService characterService){
        this.characterService = characterService;
    }

    @GetMapping
    public List<CharacterDTO> getAllCharacters(){
       return characterService.getAllCharacters();
    }

    @GetMapping("/byId/{id}")
    public CharacterDTO getCharacterById(@PathVariable("id") int id){
        return characterService.getCharacterById(id);
    }

    @GetMapping("/byName/{name}")
    public CharacterDTO getCharacterById(@PathVariable("name") String name){
        return characterService.getCharacterByName(name);
    }

    @PostMapping("/save")
    public CharacterDTO saveCharacter(@RequestBody CharacterDTO characterDTO){
        return characterService.saveCharacter(characterDTO);
    }

    @DeleteMapping("/delete/{id}")
    public int deleteCharacterById(@PathVariable("id") int id){
       return characterService.deleteCharacterById(id);
    }
}
