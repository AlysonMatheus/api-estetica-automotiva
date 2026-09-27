package com.alyson.apiestetica.execption;

public class ClienteNaoEncontradoException extends RuntimeException {
    public ClienteNaoEncontradoException(String message) {
        super(message);
    }
}
