package com.dancesar.cliente.escola.gradecurricular.handler;

import com.dancesar.cliente.escola.gradecurricular.exception.MateriaException;
import com.dancesar.cliente.escola.gradecurricular.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Map<String,String>>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException m){
        Map<String,String> erros = new HashMap<>();

        m.getBindingResult().getAllErrors().forEach(erro->{
            String campo = ((FieldError)erro).getField();
            String mensagem = erro.getDefaultMessage();
            erros.put(campo,mensagem);
        });

         Response<Map<String,String>> reponse = new Response<>();
        reponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        reponse.setData(erros);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reponse);
    }

    @ExceptionHandler(MateriaException.class)
    public ResponseEntity<Response<String>> handlerMateriaException(MateriaException m){
        Response<String> reponse = new Response<>();
        reponse.setStatusCode(m.getHttpStatus().value());
        reponse.setData(m.getMessage());
        return ResponseEntity.status(m.getHttpStatus()).body(reponse);
    }
}