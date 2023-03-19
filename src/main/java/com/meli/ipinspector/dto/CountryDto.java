package com.meli.ipinspector.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CountryDto {

    private String name;
    private String officialLanguage;
    private String officialCurrency;

}
