package com.alyson.apiestetica.controller;

import com.alyson.apiestetica.entity.request.CarroRequestDTO;
import com.alyson.apiestetica.entity.response.CarroResponseDTO;
import com.alyson.apiestetica.services.CarroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {

    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<CarroResponseDTO> cadastrar(@PathVariable Long id, @RequestBody CarroRequestDTO dto) {
        var cadastrar = carroService.cadastrar(id, dto);
        return ResponseEntity.ok(cadastrar);

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<CarroResponseDTO> atualizar(@PathVariable Long id, @RequestBody CarroRequestDTO dto) {
        var atualizar = carroService.atualizarCarro(id, dto);
        return ResponseEntity.ok(atualizar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroResponseDTO> buscarCarro(@PathVariable Long id) {
        var buscar = carroService.buscarCarro(id);
        return ResponseEntity.ok(buscar);

    }

    @GetMapping("cliente/{ìd}")
    public ResponseEntity<List<CarroResponseDTO>> buscarCarroporCliente(@PathVariable Long id) {
        var buscarPorCliente = carroService.buscarCarroCliente(id);
        return ResponseEntity.ok(buscarPorCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        carroService.excluir(id);
        return ResponseEntity.noContent().build();


    }
}
