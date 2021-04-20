package com.dancesar.cliente.escola.gradecurricular.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
public class Response<T> extends RepresentationModel<Response<T>> {
    private Integer statusCode;
    private T data;
    private Long timeStamp;

    public Response(){
        this.timeStamp = System.currentTimeMillis();
    }
}