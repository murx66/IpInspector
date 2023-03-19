package com.meli.ipinspector.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ErrorIpInspectorResponseDto extends IpInspectionResponseDto{

    private String errorMessage;

}
