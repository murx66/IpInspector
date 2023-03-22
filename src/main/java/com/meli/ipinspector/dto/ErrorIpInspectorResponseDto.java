package com.meli.ipinspector.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(callSuper = true)
public class ErrorIpInspectorResponseDto extends IpInspectionResponseDto{

    private String errorMessage;

}
