package com.kniziol.serwer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoyaltyCard {
    private Long id;
    private Date expirationDate;
    private BigDecimal discount;
    private int moviesNumber;

}
