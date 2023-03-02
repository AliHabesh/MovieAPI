package com.example.MovieAPI.service;

import com.example.MovieAPI.dto.CharacterDTO;
import com.example.MovieAPI.dto.MovieDTO;
import com.example.MovieAPI.mapper.CharacterDtoMapper;
import com.example.MovieAPI.mapper.CharacterDtoMapperImplementation;
import com.example.MovieAPI.model.Character;
import com.example.MovieAPI.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {

    private CharacterRepository characterRepository;
    private CharacterDtoMapperImplementation characterDtoMapperImplementation;


    public CharacterService(CharacterRepository characterRepository, CharacterDtoMapperImplementation characterDtoMapperImplementation) {
        this.characterRepository = characterRepository;
        this.characterDtoMapperImplementation = characterDtoMapperImplementation;
    }

    public CharacterDTO saveCharacter(CharacterDTO characterDTO){
        if (characterDTO == null) return null;
        Character character = characterRepository.save(characterDtoMapperImplementation.characterDtoToCharacter(characterDTO));
        return character != null ? characterDtoMapperImplementation.characterToCharacterDto(character):null;
    }

    public CharacterDTO getCharacterByName(String name){
        if (name== null || name.isEmpty()) return null;

        Character character = characterRepository.findByFullName(name);
        return character != null ? characterDtoMapperImplementation.characterToCharacterDto(character):null;
    }

    public CharacterDTO getCharacterById(int id){
        if (id <= 0) return null;

        Optional<Character> characterOptional = characterRepository.findById(id);
        Character character = characterOptional.get();

        return character != null ? characterDtoMapperImplementation.characterToCharacterDto(character):null;
    }

    public List<CharacterDTO> getAllCharacters(){
        List<Character> characters = characterRepository.findAll();
        List<CharacterDTO> characterDTOList = convertListCharactersToDto(characters);
        return characterDTOList != null ? characterDTOList: null;
    }

    private List<CharacterDTO> convertListCharactersToDto(List<Character> characters){
        if (characters == null || characters.isEmpty())
            return null;

        ArrayList<CharacterDTO> characterDTOArrayList = new ArrayList<>();
        characters.forEach(value -> characterDTOArrayList.add(characterDtoMapperImplementation.characterToCharacterDto(value)));
        return characterDTOArrayList;
    }

    public CharacterDTO addMovieToCharacter(MovieDTO movieDTO, String characterName){
        CharacterDTO characterDTO = characterDtoMapperImplementation.characterToCharacterDto(characterRepository.findByFullName(characterName));
        return null;
    }

    public int deleteCharacterById(Integer id){
        characterRepository.deleteById(id);
        return 1;
    }
}
