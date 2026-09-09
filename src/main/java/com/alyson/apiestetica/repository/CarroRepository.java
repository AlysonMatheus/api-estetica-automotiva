package com.alyson.apiestetica.repository;

import com.alyson.apiestetica.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  CarroRepository  extends JpaRepository<Carro, Long> {
    List<Carro> findByClienteId(@Param("id") Long id);
}
