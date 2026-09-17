package com.alyson.apiestetica.repository;

import com.alyson.apiestetica.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
//    List<Cliente> findByClienteId(@Param("id") Long id);
}
