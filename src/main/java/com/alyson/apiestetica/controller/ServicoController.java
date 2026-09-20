package com.alyson.apiestetica.controller;

import com.alyson.apiestetica.entity.request.ServicoRequestDTO;
import com.alyson.apiestetica.entity.response.ServicoResponseDTO;
import com.alyson.apiestetica.services.ServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servico")
@Tag(
        name = "Serviços",
        description = "Endpoints para gerenciamento dos serviços oferecidos pela estética"
)
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @Operation(
            summary = "Cadastrar serviço",
            description = "Cadastra um novo serviço disponível na estética, incluindo nome, descrição, preço e duração."
    )
    @PostMapping("/cadastrar")
    public ResponseEntity<ServicoResponseDTO> cadastrar(@RequestBody ServicoRequestDTO dto) {
        var cadastro = servicoService.cadastrar(dto);
        return ResponseEntity.ok(cadastro);
    }

    @Operation(
            summary = "Atualizar serviço",
            description = "Atualiza as informações de um serviço existente, como nome, descrição, preço e duração."
    )
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ServicoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ServicoRequestDTO dto) {
        var atualizar = servicoService.atualizar(id, dto);
        return ResponseEntity.ok(atualizar);

    }

    @Operation(
            summary = "Buscar serviço por ID",
            description = "Retorna os dados de um serviço específico a partir do seu identificador."
    )

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ServicoResponseDTO> buscarServico(@PathVariable Long id) {
        var buscarServico = servicoService.buscarServico(id);
        return ResponseEntity.ok(buscarServico);

    }

    @Operation(
            summary = "Listar serviços",
            description = "Retorna todos os serviços cadastrados no sistema."
    )

    @GetMapping
    public ResponseEntity<List<ServicoResponseDTO>> listarServico() {
        var listarServico = servicoService.listarServicos();
        return ResponseEntity.ok(listarServico);

    }
    @Operation(
            summary = "Ativar serviço",
            description = "Ativa um serviço previamente desativado, permitindo que ele volte a ser utilizado em novos agendamentos."
    )

    @PatchMapping("/ativar/{id}")
    public ResponseEntity<Void> ativarServico(@PathVariable Long id) {
        servicoService.ativarServico(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(
            summary = "Desativar serviço",
            description = "Desativa um serviço sem removê-lo do banco de dados, preservando o histórico dos agendamentos anteriores."
    )

    @PatchMapping("/desativar/{id}")
    public ResponseEntity<Void> desativarServico(@PathVariable Long id) {
        servicoService.desativarServico(id);
        return ResponseEntity.noContent().build();
    }


}
