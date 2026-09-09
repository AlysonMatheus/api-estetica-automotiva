package com.alyson.apiestetica.entity.response;

import com.alyson.apiestetica.entity.AgendamentoServico;

import java.math.BigDecimal;

public record AgendamentoServicoResponseDTO(
        Long idServico,
        String nomeServico,
        BigDecimal precoCobrado
) {

    public AgendamentoServicoResponseDTO(AgendamentoServico agendamentoServico) {
        this(
                agendamentoServico.getServico().getIdServico(),
                agendamentoServico.getServico().getNome(),
                agendamentoServico.getPrecoCobrado()
        );
    }
}