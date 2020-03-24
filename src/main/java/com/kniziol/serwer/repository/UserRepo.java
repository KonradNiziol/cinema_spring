package com.kniziol.serwer.repository;


import com.kniziol.serwer.model.User;
import com.kniziol.serwer.repository.generic.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {

    Optional<User> getUserByEmail(String email);
}
