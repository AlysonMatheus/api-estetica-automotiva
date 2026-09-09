package com.alyson.apiestetica.repository;

import com.alyson.apiestetica.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
