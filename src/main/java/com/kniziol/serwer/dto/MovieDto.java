package com.kniziol.serwer.dto;

import com.kniziol.serwer.model.enums.Genre;
import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {

    private String title;
    private Genre genre;
    private int duration;
    private Date releaseDate;

}
