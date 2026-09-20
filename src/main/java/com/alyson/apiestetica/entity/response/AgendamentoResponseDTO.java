package com.alyson.apiestetica.entity.response;

import com.alyson.apiestetica.entity.Agendamento;
import com.alyson.apiestetica.enums.StatusAgendamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record AgendamentoResponseDTO(
        Long idAgendamento,
        Long idVeiculo,
        LocalDateTime horarioInicio,
        LocalDateTime horarioFinal,
        StatusAgendamento status,
        String observacao,
        LocalDateTime createdAt,
        List<AgendamentoServicoResponseDTO> servicos,
        BigDecimal valorTotal

) {

    public AgendamentoResponseDTO(Agendamento agendamento) {
        this(
                agendamento.getIdAgendamento(),
                agendamento.getVeiculo().getIdVeiculo(),
                agendamento.getHorarioInicio(),
                agendamento.getHorarioFinal(),
                agendamento.getStatus(),
                agendamento.getObservacao(),
                agendamento.getCreatedAt(),
                agendamento.getServicos()
                        .stream()
                        .map(AgendamentoServicoResponseDTO::new)
                        .toList(),
                agendamento.calcularValorTotal()


        );
    }
}