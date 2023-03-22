package com.meli.ipinspector.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CountryResponseDto {

    private String ip;
    private String name;
    private String officialLanguage;
    private String officialCurrency;

}
