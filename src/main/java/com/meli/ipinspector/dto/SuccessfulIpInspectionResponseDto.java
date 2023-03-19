package com.meli.ipinspector.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class SuccessfulIpInspectionResponseDto extends ErrorIpInspectorResponseDto {

    private String country;
    private Double cotization;

}
