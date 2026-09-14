package com.alyson.apiestetica.repository;

import com.alyson.apiestetica.entity.Carro;
import com.alyson.apiestetica.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  CarroRepository  extends JpaRepository<Carro, Long> {
//    List<Cliente> findByClienteId(@Param("id") Long id);
}
