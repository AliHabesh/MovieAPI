package com.example.MovieAPI.mapper;

import com.example.MovieAPI.dto.CharacterDTO;
import com.example.MovieAPI.model.Character;
import com.example.MovieAPI.model.Movie;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CharacterDtoMapperImplementation implements CharacterDtoMapper{
    @Override
    public CharacterDTO characterToCharacterDto(Character car) {
        if (car == null) {
            return null;
        } else {
            CharacterDTO characterDTO = new CharacterDTO();
            characterDTO.setCharacterId(car.getCharacterId());
            characterDTO.setFullName(car.getFullName());
            characterDTO.setAlias(car.getAlias());
            characterDTO.setGender(car.getGender());
            characterDTO.setPicture(car.getPicture());
            if (car.getMovies() != null)
                    characterDTO.setMovie(car.getMovies()
                            .stream()
                            .map(Movie::getMovieId)
                            .collect(Collectors.toList()));
            else
                characterDTO.setMovie(null);
            return characterDTO;
        }
    }

    @Override
    public Character characterDtoToCharacter(CharacterDTO characterDTO) {
        if (characterDTO == null) {
            return null;
        } else {
            Character character = new Character();
            character.setCharacterId(characterDTO.getCharacterId());
            character.setFullName(characterDTO.getFullName());
            character.setAlias(characterDTO.getAlias());
            character.setGender(characterDTO.getGender());
            character.setPicture(characterDTO.getPicture());
            return character;
        }
    }
}
