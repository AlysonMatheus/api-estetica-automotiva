package com.alyson.apiestetica.entity.request;

import com.alyson.apiestetica.entity.Agendamento;
import jakarta.validation.Valid;


import java.time.LocalDateTime;
import java.util.List;

public record AgendamentoRequestDTO(
        Long idVeiculo,
        LocalDateTime horarioInicio,
        LocalDateTime horarioFinal,
        String observacao,
      @Valid List<AgendamentoServicoRequestDTO> servicos

) {

}