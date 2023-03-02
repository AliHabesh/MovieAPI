package com.example.MovieAPI.mapper;

import com.example.MovieAPI.dto.MovieDTO;
import com.example.MovieAPI.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovieDtoMapper {

    MovieDtoMapper INSTANCE = Mappers.getMapper(MovieDtoMapper.class);
    MovieDTO movieToMovieDto(Movie mov);
    Movie movieDtoToMovie(MovieDTO movieDTO);
}
