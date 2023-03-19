package com.meli.ipinspector.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class DailyPriceDto {

    private String country;
    private double cotization;
    private LocalDateTime cotizationDate;

}
