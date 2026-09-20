package com.alyson.apiestetica.entity.response;

import com.alyson.apiestetica.entity.Veiculo;
import com.alyson.apiestetica.enums.CategoriaVeiculo;

public record VeiculoResponseDTO(
        Long idVeiculo,
        Long idCliente,
        String modelo,
        String marca,
        String placa,
        String cor,
        Integer ano,
        CategoriaVeiculo categoriaVeiculo
) {
    public VeiculoResponseDTO(Veiculo veiculo) {
        this(
                veiculo.getIdVeiculo(),
                veiculo.getCliente().getIdCliente(),
                veiculo.getModelo(),
                veiculo.getMarca(),
                veiculo.getPlaca(),
                veiculo.getCor(),
                veiculo.getAno(),
                veiculo.getCategoria()


        );
    }
}
