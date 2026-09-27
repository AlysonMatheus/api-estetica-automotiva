package com.alyson.apiestetica.execption;

public class ServicoNaoEncontradoException extends RuntimeException {
    public ServicoNaoEncontradoException(String message) {
        super(message);
    }
}
