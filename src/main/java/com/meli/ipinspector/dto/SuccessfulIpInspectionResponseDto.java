package com.meli.ipinspector.dto;

import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
@EqualsAndHashCode(callSuper = true)
public class SuccessfulIpInspectionResponseDto extends IpInspectionResponseDto {

    private CountryDto country;
    private Double dailyPrice;

}
