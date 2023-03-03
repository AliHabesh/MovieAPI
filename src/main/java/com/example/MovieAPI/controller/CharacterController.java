package com.example.MovieAPI.controller;

import com.example.MovieAPI.dto.CharacterDTO;
import com.example.MovieAPI.service.CharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong",
                    content = @Content)
    })
    @GetMapping
    public List<CharacterDTO> getAllCharacters(){
       return characterService.getAllCharacters();
    }

    @Operation(summary = "Get character with id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Character with provided id found successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No character found with provided id",
                    content = @Content)
    })
    @GetMapping("/byId/{id}")
    public CharacterDTO getCharacterById(@PathVariable("id") int id){
        return characterService.getCharacterById(id);
    }

    @Operation(summary = "Get character by name")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Character with provided name found successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No character found with this name",
                    content = @Content)
    })
    @GetMapping("/byName/{name}")
    public CharacterDTO getCharacterById(@PathVariable("name") String name){
        return characterService.getCharacterByName(name);
    }

    @Operation(summary = "Add or update movie")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Character successfully added/updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong",
                    content = @Content)
    })
    @PostMapping("/save")
    public CharacterDTO saveCharacter(@RequestBody CharacterDTO characterDTO){
        return characterService.saveCharacter(characterDTO);
    }

    @Operation(summary = "Delete character")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Character successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong",
                    content = @Content)
    })
    @DeleteMapping("/delete/{id}")
    public int deleteCharacterById(@PathVariable("id") int id){
       return characterService.deleteCharacterById(id);
    }
}
