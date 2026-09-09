package com.alyson.apiestetica.repository;

import com.alyson.apiestetica.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
