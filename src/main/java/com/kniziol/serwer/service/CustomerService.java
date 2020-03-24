package com.kniziol.serwer.service;



import com.kniziol.exception.AppException;
import com.kniziol.serwer.repository.UserRepo;

import com.kniziol.serwer.model.LoyaltyCard;
import com.kniziol.serwer.model.User;
import com.kniziol.serwer.repository.tools.Mappers;
import com.kniziol.serwer.dto.LoginCredentials;
import com.kniziol.serwer.dto.UserCreateDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@AllArgsConstructor
public class CustomerService {

    private final UserRepo userRepo;
    private final LoyaltyCardService loyaltyCardService;

    public boolean createCustomer(UserCreateDto userCreateDto){
        if (!userCreateDto.getPassword().equals(userCreateDto.getRepeatPassword())){
            throw new AppException("Passwords should be the same!");
        }

        User user = Mappers.fromCompanyToCompanyDto(userCreateDto);
        user.setLoyaltyCardId(loyaltyCardService.createLoyaltyCard());
        return userRepo.add(user).isPresent();
    }

    public boolean loginCustomer(LoginCredentials loginCredentials){
        String enteredEmail = loginCredentials.getEmail();
        if (enteredEmail == null || enteredEmail.isEmpty()){
            throw new AppException("Entered email is empty!");
        }
        AtomicBoolean isCorrectPassword = new AtomicBoolean(false);
        Optional<User> user = userRepo.getUserByEmail(enteredEmail);
        user.ifPresentOrElse(
                user1 -> isCorrectPassword.set(user1.getPassword().equals(loginCredentials.getPassword())),
                () -> new AppException(String.format("User with email: %s doesn't exist", enteredEmail)));

        return isCorrectPassword.get();
    }
}
