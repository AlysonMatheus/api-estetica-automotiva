package com.alyson.apiestetica.entity.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AgendamentoServicoRequestDTO(

        @NotNull(message = "O serviço é obrigatório")
        Long idServico,

        @NotNull(message = "O preço cobrado é obrigatório")
        @DecimalMin(value = "0.00", message = "O preço cobrado não pode ser negativo")
        BigDecimal precoCobrado

) {
}