package com.kniziol.serwer.service;

import com.kniziol.serwer.dto.MovieDto;
import com.kniziol.serwer.repository.MoviesRepo;
import com.kniziol.serwer.repository.tools.Mappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MoviesService {

    private final MoviesRepo moviesRepo;

    public List<MovieDto> getAllMovies(){

        return moviesRepo.findAll().stream()
                .map(movie -> Mappers.fromMovieToMovieDto(movie))
                .collect(Collectors.toList());
    }
}
