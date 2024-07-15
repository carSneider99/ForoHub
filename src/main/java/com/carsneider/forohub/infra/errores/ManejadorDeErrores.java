package com.carsneider.forohub.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ManejadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity tratarError400(Exception e){
        List<DatosErrorValidacion> errores = new ArrayList<>();

        if (e instanceof HttpMessageNotReadableException)
            errores.add(new DatosErrorValidacion("Error parámetro", e.getMessage()));

        if (e instanceof MethodArgumentNotValidException exception)
            errores.addAll(exception.getFieldErrors().stream().map(DatosErrorValidacion::new).toList());

        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ValidacionDeIntegridad.class)
    public ResponseEntity errorHandlerValidacionesIntegridad(Exception e){
        return ResponseEntity.badRequest().body(new DatosErrorValidacion("error",e.getMessage()));
    }

    private record DatosErrorValidacion (String campo, String error){
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}