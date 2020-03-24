package com.kniziol.serwer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String name;
    private String surname;
    private String password;
    private int age;
    private String email;
    private long loyaltyCardId;

}
