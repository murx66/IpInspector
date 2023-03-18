package com.meli.ipinspector.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class IpInspectorException extends Exception {

    private String message;

}
