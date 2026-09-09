package com.alyson.apiestetica.entity.request;

import com.alyson.apiestetica.entity.Carro;
import com.alyson.apiestetica.entity.Cliente;

import java.util.List;

public record ClienteRequestDTO(
        String nome,
        String telefone,
        String rua,
        String bairro,
        String numero
) {
}