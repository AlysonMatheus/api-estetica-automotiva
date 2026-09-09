package com.alyson.apiestetica.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "agendamento_servico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgendamentoServico {

    @EmbeddedId
    private AgendamentoServicoId idServico;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idAgendamento")
    @JoinColumn(name = "id_agendamento")
    private Agendamento agendamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idServico")
    @JoinColumn(name = "id_servico")
    private Servico servico;

    @Column(name = "preco_cobrado", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoCobrado;


}