package com.kniziol.serwer.repository.tools;

import com.kniziol.serwer.dto.MovieDto;
import com.kniziol.serwer.model.Movies;
import com.kniziol.serwer.model.User;
import com.kniziol.serwer.dto.UserCreateDto;

public interface Mappers {

    static User fromCompanyToCompanyDto(UserCreateDto user) {
        return user == null ? null : User.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }

    static MovieDto fromMovieToMovieDto(Movies movie){
        return movie == null ? null : MovieDto.builder()
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .duration(movie.getDuration())
                .releaseDate(movie.getReleaseDate())
                .build();
    }
}
