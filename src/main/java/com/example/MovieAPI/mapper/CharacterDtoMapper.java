package com.example.MovieAPI.mapper;

import com.example.MovieAPI.dto.CharacterDTO;
import com.example.MovieAPI.model.Character;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface CharacterDtoMapper {
    CharacterDtoMapper INSTANCE = Mappers.getMapper(CharacterDtoMapper.class);
    CharacterDTO characterToCharacterDto(Character car);
    Character characterDtoToCharacter(CharacterDTO characterDTO);
}
