package com.kniziol.serwer.model;


import com.kniziol.serwer.model.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movies {
    private Integer id;
    private String title;
    private Genre genre;
    private BigDecimal price;
    private int duration;
    private Date releaseDate;
}
