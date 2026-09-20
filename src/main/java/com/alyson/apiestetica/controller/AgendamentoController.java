package com.alyson.apiestetica.controller;

import com.alyson.apiestetica.entity.request.AgendamentoRequestDTO;
import com.alyson.apiestetica.entity.response.AgendamentoResponseDTO;
import com.alyson.apiestetica.enums.StatusAgendamento;
import com.alyson.apiestetica.services.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
@Tag(
        name = "Agendamentos",
        description = "Endpoints para criação e gerenciamento dos agendamentos e seus serviços"
)
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

       @Operation(
            summary = "Cadastrar agendamento",
            description = "Cria um novo agendamento para um veículo existente e vincula os serviços selecionados, registrando o preço cobrado de cada serviço no momento do agendamento."
    )
    @PostMapping("/cadastrar")
    public ResponseEntity<AgendamentoResponseDTO> cadastrar(@Valid @RequestBody AgendamentoRequestDTO dto) {
        var cadastrar = agendamentoService.cadastrar(dto);
        return ResponseEntity.ok(cadastrar);
    }
       @Operation(
            summary = "Atualizar agendamento",
            description = "Atualiza os dados de um agendamento existente, como horários e observação."
    )

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<AgendamentoResponseDTO> atualizar(@PathVariable Long id, @RequestBody AgendamentoRequestDTO dto) {
        var atualizar = agendamentoService.editar(id, dto);
        return ResponseEntity.ok(atualizar);
    }
       @Operation(
            summary = "Excluir agendamento",
            description = "Remove um agendamento do sistema a partir do seu identificador."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> excluir(@PathVariable Long id) {
        agendamentoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Buscar agendamento por ID",
            description = "Retorna os dados de um agendamento específico, incluindo veículo, horários, status, observação e serviços vinculados."
    )

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> buscarAgendamento(@PathVariable Long id) {
        var buscar = agendamentoService.buscarAgendamento(id);
        return ResponseEntity.ok(buscar);
    }

    //    @PostMapping("/vincular")
//    public ResponseEntity<AgendamentoResponseDTO> vincularServicos(@RequestBody AgendamentoRequestDTO dto) {
//        var vincular = agendamentoService.vincularServicos(dto);
//        return ResponseEntity.ok(vincular);
//    }
    @Operation(
            summary = "Listar agendamentos",
            description = "Retorna todos os agendamentos cadastrados no sistema."
    )

    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDTO>> listarTodos() {
        var listar = agendamentoService.listartodos();
        return ResponseEntity.ok(listar);
    }
    @Operation(
            summary = "Alterar status do agendamento",
            description = "Altera o status atual do agendamento de acordo com o fluxo definido pela regra de negócio."
    )
    @PatchMapping("/{id}/status")
    public ResponseEntity<AgendamentoResponseDTO> alterarStatus(@PathVariable Long id, @RequestParam StatusAgendamento status) {
        var agendamento = agendamentoService.alterarStatus(id, status);
        return ResponseEntity.ok(agendamento);
    }


}
