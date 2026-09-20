package com.alyson.apiestetica.entity;

import com.alyson.apiestetica.entity.request.AgendamentoRequestDTO;
import com.alyson.apiestetica.enums.StatusAgendamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agendamento")
    private Long idAgendamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_veiculo", nullable = false)
    private Veiculo veiculo;

    @Column(name = "horario_inicio", nullable = false)
    private LocalDateTime horarioInicio;

    @Column(name = "horario_final", nullable = false)
    private LocalDateTime horarioFinal;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private StatusAgendamento status = StatusAgendamento.AGENDADO;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(
            mappedBy = "agendamento",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AgendamentoServico> servicos = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }

        if (status == null) {
            status = StatusAgendamento.AGENDADO;
        }
    }
    public Agendamento(
            AgendamentoRequestDTO dto,
            Veiculo veiculo
    ) {
        this.veiculo = veiculo;
        this.horarioInicio = dto.horarioInicio();
        this.horarioFinal = dto.horarioFinal();
        this.observacao = dto.observacao();
        this.status = StatusAgendamento.AGENDADO;

    }
    public void atualizarDados(AgendamentoRequestDTO dto) {
        this.horarioInicio = dto.horarioInicio();
        this.horarioFinal = dto.horarioFinal();
        this.observacao = dto.observacao();
    }
    public BigDecimal calcularValorTotal() {
        return this.servicos
                .stream()
                .map(AgendamentoServico::getPrecoCobrado)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}