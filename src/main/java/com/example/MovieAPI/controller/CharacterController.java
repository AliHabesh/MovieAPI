package com.example.MovieAPI.controller;

import com.example.MovieAPI.dto.CharacterDTO;
import com.example.MovieAPI.service.CharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/characters")
public class CharacterController {

    private CharacterService characterService;


    public CharacterController(CharacterService characterService){
        this.characterService = characterService;
    }

    @Operation(summary = "Get all characters")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Characters found successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity getAllCharacters(){
        List<CharacterDTO> list = characterService.getAllCharacters();
        if (list != null)
            return ResponseEntity.ok(list);


        return ResponseEntity.status(400).body("Something went wrong");

    }

    @Operation(summary = "Get character with id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Character with provided id found successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "No character found with provided id",
                    content = @Content)
    })
    @GetMapping("/byId/{id}")
    public ResponseEntity getCharacterById(@PathVariable("id") int id){
        CharacterDTO characterDTO = characterService.getCharacterById(id);
        if (characterDTO != null)
            return ResponseEntity.ok(characterDTO);

        return ResponseEntity.status(404).body("No character found with provided id");
    }

    @Operation(summary = "Get character by name")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Character with provided name found successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class)) }),

            @ApiResponse(responseCode = "400",
                    description = "No character found with the provided name",
                    content = @Content)
    })
    @GetMapping("/byName/{name}")
    public ResponseEntity getCharacterById(@PathVariable("name") String name){
        CharacterDTO characterDTO = characterService.getCharacterByName(name);
        if (characterDTO == null)
            return ResponseEntity.status(404).body("No character found with the provided name");

        return ResponseEntity.ok(characterDTO);


    }

    @Operation(summary = "Add or update movie")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Character successfully added/updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Something went wrong with saving the character",
                    content = @Content)
    })
    @PostMapping("/save")
    public ResponseEntity saveCharacter(@RequestBody CharacterDTO characterDTO){
        CharacterDTO characterDTO1 = characterService.saveCharacter(characterDTO);
        System.out.println(characterDTO + "AND "+characterDTO1);
        if (characterDTO1.getFullName() != characterDTO.getFullName())
            return ResponseEntity.status(404).body("Something went wrong with saving the character");

        return ResponseEntity.ok(characterDTO1);
    }

    @Operation(summary = "Delete character")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Character successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong with deleting character",
                    content = @Content)
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCharacterById(@PathVariable("id") int id){
        int isDeleted = characterService.deleteCharacterById(id);
        System.out.println(isDeleted);
        return isDeleted == 1 ? ResponseEntity.ok("Character successfully deleted") :
                                ResponseEntity.status(404).body("Something went wrong with deleting character");
    }

}
