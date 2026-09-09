package com.alyson.apiestetica.entity.response;

import com.alyson.apiestetica.entity.Servico;

import java.math.BigDecimal;

public record ServicoResponseDTO(
        Long idServico,
        String nome,
        String descricao,
        BigDecimal preco,
        Integer duracaoMinutos,
        Boolean ativo
) {

    public ServicoResponseDTO(Servico servico) {
        this(
                servico.getIdServico(),
                servico.getNome(),
                servico.getDescricao(),
                servico.getPreco(),
                servico.getDuracaoMinutos(),
                servico.getAtivo()
        );
    }
}