package com.kniziol.serwer.repository;


import com.kniziol.serwer.model.Movies;
import com.kniziol.serwer.repository.generic.CrudRepository;

public interface MoviesRepo extends CrudRepository<Movies, Long> {
}
