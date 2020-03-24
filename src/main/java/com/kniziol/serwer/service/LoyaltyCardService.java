package com.kniziol.serwer.service;

import com.kniziol.exception.AppException;
import com.kniziol.serwer.model.LoyaltyCard;
import com.kniziol.serwer.repository.LoyaltyCardRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoyaltyCardService {

    private final LoyaltyCardRepo loyaltyCardRepo;

    public long createLoyaltyCard(){
        LoyaltyCard loyaltyCard = LoyaltyCard.builder()
                .expirationDate(Date.valueOf(LocalDate.now()))
                .discount(new BigDecimal("0"))
                .moviesNumber(0)
                .build();
        Optional<LoyaltyCard> loyaltyCardOptional = loyaltyCardRepo.add(loyaltyCard);
        return loyaltyCardOptional.orElseThrow(() -> new AppException("Can not create new Loyalty Card")).getId();
    }
}
