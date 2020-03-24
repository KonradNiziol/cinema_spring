package com.kniziol.web.controller;

import com.kniziol.serwer.repository.MoviesRepo;
import com.kniziol.serwer.service.MoviesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MoviesController {

    private final MoviesService moviesService;

    @GetMapping("/all")
    public String getAllMovies(Model model){
        model.addAttribute("movies", moviesService.getAllMovies());
        return "movies/all";
    }

}
