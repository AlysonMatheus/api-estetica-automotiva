package com.alyson.apiestetica.entity.response;

import com.alyson.apiestetica.entity.Cliente;

public record ClienteResponseDTO(
        Long idCliente,
        String nome,
        String telefone,
        String rua,
        String bairro,
        String numero
) {

    public ClienteResponseDTO(Cliente cliente) {
        this(
                cliente.getIdCliente(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getRua(),
                cliente.getBairro(),
                cliente.getNumero()
        );
    }
}
