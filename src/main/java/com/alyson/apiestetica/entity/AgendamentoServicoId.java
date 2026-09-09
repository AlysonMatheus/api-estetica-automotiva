package com.alyson.apiestetica.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AgendamentoServicoId implements Serializable {

    @Column(name = "id_agendamento")
    private Long idAgendamento;

    @Column(name = "id_servico")
    private Long idServico;
}