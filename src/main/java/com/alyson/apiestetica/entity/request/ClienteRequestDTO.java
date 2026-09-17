package com.alyson.apiestetica.entity.request;

public record ClienteRequestDTO(
        String nome,
        String telefone,
        String rua,
        String bairro,
        String numero
) {
}