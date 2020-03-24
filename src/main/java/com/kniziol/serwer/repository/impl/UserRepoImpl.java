package com.kniziol.serwer.repository.impl;


import com.kniziol.serwer.model.User;
import com.kniziol.serwer.repository.UserRepo;
import com.kniziol.serwer.repository.generic.AbstractCrudRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserRepoImpl extends AbstractCrudRepository<User, Long> implements UserRepo {

    @Override
    public Optional<User> getUserByEmail(String email) {
        final String SQL = "select * from " + User.class + " where email = :email";
        return dbConnection.getJdbi().withHandle(handle -> handle
                .createQuery(SQL)
                .bind("email", email)
                .mapToBean(User.class)
                .findFirst());
    }
}
