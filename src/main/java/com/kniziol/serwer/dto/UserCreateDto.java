package com.kniziol.serwer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDto {

    private String name;
    private String surname;
    private int age;
    private String email;
    private String password;
    private String repeatPassword;


}
