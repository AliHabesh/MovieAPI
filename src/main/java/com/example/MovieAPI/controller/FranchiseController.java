package com.example.MovieAPI.controller;


import com.example.MovieAPI.dto.FranchiseDTO;
import com.example.MovieAPI.service.FranchiseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/franchises")
public class FranchiseController {

    private FranchiseService franchiseService;

    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }



    @Operation(summary = "Get all franchises")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Franchises found successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FranchiseDTO.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong",
                    content = @Content)
    })
    @GetMapping
    public List<FranchiseDTO> getAllFranchises(){
        return franchiseService.getAllFranchises();
    }

    @Operation(summary = "Get franchise with id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Franchise with provided id found successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FranchiseDTO.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong",
                    content = @Content)
    })
    @GetMapping("/byId/{id}")
    public FranchiseDTO getFranchiseById(@PathVariable("id") int id){
        return franchiseService.getFranchiseById(id);
    }

    @Operation(summary = "Get all characters in on franchise")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Franchises found successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong",
                    content = @Content)
    })
    @GetMapping("/byId/{id}/characters")
    public List<Integer> getAllCharactersInAFranchise(@PathVariable("id") int id){
        return franchiseService.getAllCharactersInFranchise(id);
    }

    @Operation(summary = "Get all movies in on franchise")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Franchises found successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong",
                    content = @Content)
    })
    @GetMapping("/byId/{id}/movies")
    public List<Integer> getAllMoviesInAFranchise(@PathVariable("id") int id){
        return franchiseService.getAllMoviesInFranchise(id);
    }

    @Operation(summary = "Get franchise with name")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Franchise with provided name found successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FranchiseDTO.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong",
                    content = @Content)
    })
    @GetMapping("/byName/{name}")
    public FranchiseDTO getFranchiseByName(@PathVariable("name") String name){
        return franchiseService.getFranchiseByName(name);
    }


    @Operation(summary = "Add or update franchise")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Franchise successfully added/updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong",
                    content = @Content)
    })
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


    @Operation(summary = "Delete franchise")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Franchise successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong",
                    content = @Content)
    })
    @DeleteMapping("/delete/{id}")
    public int deleteFranchiseById(@PathVariable("id") int id){
        return franchiseService.deleteFranchiseById(id);
    }
}
