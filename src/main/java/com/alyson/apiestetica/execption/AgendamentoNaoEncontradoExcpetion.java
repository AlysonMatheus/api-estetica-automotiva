package com.alyson.apiestetica.execption;

public class AgendamentoNaoEncontradoExcpetion extends RuntimeException {
    public AgendamentoNaoEncontradoExcpetion(String message){
        super(message);
    }
}
