package com.alyson.apiestetica.execption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
   ClienteNaoEncontradoException.class,
            VeiculoNaoEncontradoException.class,
            ServicoNaoEncontradoException.class,
            AgendamentoNaoEncontradoExcpetion.class
    })
        public ResponseEntity<Erro>naoEcontrado(RuntimeException ex){
        Erro erro = new Erro(ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
        }
}
