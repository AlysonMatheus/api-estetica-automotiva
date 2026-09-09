package com.alyson.apiestetica.entity.request;

import java.math.BigDecimal;

public record ServicoRequestDTO(
        String nome,
        String descricao,
        BigDecimal preco,
        Integer duracaoMinutos
) {
}