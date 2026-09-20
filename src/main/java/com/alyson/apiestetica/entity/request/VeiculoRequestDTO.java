package com.alyson.apiestetica.entity.request;

import com.alyson.apiestetica.enums.CategoriaVeiculo;

public record VeiculoRequestDTO(
        Long idCliente,
        String modelo,
        String marca,
        String placa,
        String cor,
        Integer ano,
        CategoriaVeiculo categoria){
}
