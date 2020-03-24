package com.kniziol.serwer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesStand {
    private Long id;
    private Movies movie;
    private User user;
    private LocalDate localDate;
}
