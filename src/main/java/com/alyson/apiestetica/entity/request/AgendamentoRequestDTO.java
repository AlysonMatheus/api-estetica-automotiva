package com.alyson.apiestetica.entity.request;

import com.alyson.apiestetica.entity.Agendamento;


import java.time.LocalDateTime;
import java.util.List;

public record AgendamentoRequestDTO(
        Long idCarro,
        LocalDateTime horarioInicio,
        LocalDateTime horarioFinal,
        String observacao,
        List<Long> servicos
) {

}