package com.alyson.apiestetica.entity.response;

import com.alyson.apiestetica.entity.Carro;

public record CarroResponseDTO(
        Long idCarro,
        Long idCliente,
        String modelo,
        String marca,
        String placa,
        String cor,
        Integer ano) {
    public CarroResponseDTO(Carro carro) {
        this(
                carro.getIdCarro(),
                carro.getCliente().getIdCliente(),
                carro.getModelo(),
                carro.getMarca(),
                carro.getPlaca(),
                carro.getCor(),
                carro.getAno()


        );
    }
}
