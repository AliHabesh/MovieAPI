package com.example.MovieAPI.controller;


import com.example.MovieAPI.dto.FranchiseDTO;
import com.example.MovieAPI.model.Franchise;
import com.example.MovieAPI.service.FranchiseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
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
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity getAllFranchises(){
        List<FranchiseDTO> list = franchiseService.getAllFranchises();
        if (list != null)
               return ResponseEntity.ok(franchiseService.getAllFranchises());

        return ResponseEntity.status(400).body("Something went wrong");
    }

    @Operation(summary = "Get franchise with id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Franchise with provided id found successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FranchiseDTO.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong, the user could not be found",
                    content = @Content)
    })
    @GetMapping("/byId/{id}")
    public ResponseEntity getFranchiseById(@PathVariable("id") int id){
        FranchiseDTO franchiseDTO = franchiseService.getFranchiseById(id);
        if (franchiseDTO != null)
            return ResponseEntity.ok(franchiseDTO);

        return ResponseEntity.status(404).body("Something went wrong, the user could not be found");
    }

    @Operation(summary = "Get all characters in on franchise")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Franchises found successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong with returning a list of character ids",
                    content = @Content)
    })
    @GetMapping("/byId/{id}/characters")
    public ResponseEntity getAllCharactersInAFranchise(@PathVariable("id") int id){
        List<Integer> list = franchiseService.getAllCharactersInFranchise(id);
        if (list != null)
            return ResponseEntity.ok(list);

        return ResponseEntity.status(404).body("Something went wrong with returning a list of character ids");
    }

    @Operation(summary = "Get all movies in on franchise")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Franchises found successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Something went wrong with returning a list of movie ids",
                    content = @Content)
    })
    @GetMapping("/byId/{id}/movies")
    public ResponseEntity getAllMoviesInAFranchise(@PathVariable("id") int id){
        List<Integer> list = franchiseService.getAllMoviesInFranchise(id);
        if (list != null)
            return ResponseEntity.ok(list);

        return ResponseEntity.status(404).body("Something went wrong with returning a list of movie ids");
    }

    @Operation(summary = "Get franchise with name")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Franchise with provided name found successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FranchiseDTO.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Something went wrong",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Franchise doesn't exist",
                    content = @Content)
    })
    @GetMapping("/byName/{name}")
    public ResponseEntity getFranchiseByName(@PathVariable("name") String name){
        if (name.isEmpty())
            return ResponseEntity.status(404).body("Franchise doesnt exist");

        FranchiseDTO franchiseDTO = franchiseService.getFranchiseByName(name);
        if (franchiseDTO != null)
            return ResponseEntity.ok(franchiseDTO);

        return ResponseEntity.status(400).body("Something went wrong");
    }


    @Operation(summary = "Add or update franchise")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Franchise successfully added/updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Something went wrong",
                    content = @Content)
    })
    @PostMapping("/save")
    public ResponseEntity addFranchise(@RequestBody FranchiseDTO franchiseDTO){
        FranchiseDTO franchiseDTO1 = null;
        if (franchiseDTO.getName() != null)
               franchiseDTO1 = franchiseService.saveFranchise(franchiseDTO);

        if (franchiseDTO1 != null)
            return ResponseEntity.ok(franchiseDTO1);

        return ResponseEntity.status(400).body("Something went wrong");
    }

    //Tested, it works

    @Operation(summary = "Add or update movies in a franchise")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Franchise successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Something went wrong",
                    content = @Content)
    })
    @PutMapping("/update/{franchiseId}/movies/{id}")
    public ResponseEntity addMovieToFranchise(@PathVariable("franchiseId") int franchiseId, @PathVariable("id") int movieId){
        FranchiseDTO franchiseDTO = franchiseService.addMovieToFranchise(franchiseId, movieId);
        if (franchiseDTO == null)
            return ResponseEntity.status(400).body("Something went wrong");


        return ResponseEntity.ok(franchiseDTO);
    }




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
    public ResponseEntity deleteFranchiseById(@PathVariable("id") int id){
        int isDeleted = franchiseService.deleteFranchiseById(id);
        if (isDeleted == -1)
            return ResponseEntity.status(404).body("Something went wrong");

        return ResponseEntity.ok("Franchise successfully deleted");
    }
}
