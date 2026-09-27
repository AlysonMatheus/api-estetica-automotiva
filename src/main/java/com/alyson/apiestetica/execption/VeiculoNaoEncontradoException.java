package com.alyson.apiestetica.execption;

public class VeiculoNaoEncontradoException extends RuntimeException {
    public VeiculoNaoEncontradoException(String message) {
        super(message);
    }
}
