package com.dancesar.cliente.escola.gradecurricular.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class ErrorMapResponse {

    private Integer httpStatus;
    private Map<String,String> erros;
    private Long timeStamp;
}