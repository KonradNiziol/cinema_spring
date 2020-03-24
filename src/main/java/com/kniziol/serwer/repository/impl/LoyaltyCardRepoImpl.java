package com.kniziol.serwer.repository.impl;

import com.kniziol.serwer.model.LoyaltyCard;
import com.kniziol.serwer.model.User;
import com.kniziol.serwer.repository.LoyaltyCardRepo;
import com.kniziol.serwer.repository.generic.AbstractCrudRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoyaltyCardRepoImpl extends AbstractCrudRepository<LoyaltyCard, Long> implements LoyaltyCardRepo {
}
