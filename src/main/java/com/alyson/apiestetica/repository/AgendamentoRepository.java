package com.alyson.apiestetica.repository;

import com.alyson.apiestetica.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    boolean existsByHorarioInicioLessThanAndHorarioFinalGreaterThan(
            LocalDateTime horarioFinal,
            LocalDateTime horarioInicio
    );
}