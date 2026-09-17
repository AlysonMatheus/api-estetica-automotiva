package com.alyson.apiestetica.entity.request;

public record VeiculoRequestDTO(
        Long idCliente,
        String modelo,
        String marca,
        String placa,
        String cor,
        Integer ano){
}
