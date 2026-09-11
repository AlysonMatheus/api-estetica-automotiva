package com.alyson.apiestetica.controller;

import com.alyson.apiestetica.entity.request.ServicoRequestDTO;
import com.alyson.apiestetica.entity.response.ServicoResponseDTO;
import com.alyson.apiestetica.services.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<ServicoResponseDTO> cadastrar(@RequestBody ServicoRequestDTO dto) {
        var cadastro = servicoService.cadastrar(dto);
        return ResponseEntity.ok(cadastro);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ServicoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ServicoRequestDTO dto) {
        var atualizar = servicoService.atualizar(id, dto);
        return ResponseEntity.ok(atualizar);

    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ServicoResponseDTO> buscarServico(@PathVariable Long id) {
        var buscarServico = servicoService.buscarServico(id);
        return ResponseEntity.ok(buscarServico);

    }

    @GetMapping
    public ResponseEntity<List<ServicoResponseDTO>> listarServico() {
        var listarServico = servicoService.listarServicos();
        return ResponseEntity.ok(listarServico);

    }

    @PatchMapping("/ativar/{id}")
    public ResponseEntity<Void> ativarServico(@PathVariable Long id) {
        servicoService.ativarServico(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("desativar/{id}")
    public ResponseEntity<Void> desativarServico(@PathVariable Long id) {
        servicoService.desativarServico(id);
        return ResponseEntity.noContent().build();
    }


}
