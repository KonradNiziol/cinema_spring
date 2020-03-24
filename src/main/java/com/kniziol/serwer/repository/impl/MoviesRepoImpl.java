package com.kniziol.serwer.repository.impl;

import com.kniziol.serwer.model.Movies;
import com.kniziol.serwer.repository.MoviesRepo;
import com.kniziol.serwer.repository.generic.AbstractCrudRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class MoviesRepoImpl extends AbstractCrudRepository<Movies, Long> implements MoviesRepo {
}
