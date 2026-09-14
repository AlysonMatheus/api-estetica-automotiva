package com.alyson.apiestetica.controller;

import com.alyson.apiestetica.entity.request.ClienteRequestDTO;
import com.alyson.apiestetica.entity.response.ClienteResponseDTO;
import com.alyson.apiestetica.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@Tag(
        name = "Clientes",
        description = "Endpoints para gerenciamento de clientes"
)
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(
            summary = "Cadastrar cliente",
            description = "Cadastra um novo cliente no sistema com seus dados pessoais e de endereço."
    )
    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteResponseDTO> cadastrar(@RequestBody ClienteRequestDTO dto) {
        var cadastrar = clienteService.cadastrar(dto);
        return ResponseEntity.ok(cadastrar);

    }

    @Operation(
            summary = "Atualizar cliente",
            description = "Atualiza os dados cadastrais de um cliente existente a partir do seu identificador."
    )

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id, @RequestBody ClienteRequestDTO dto) {
        var atualizar = clienteService.atualizar(id, dto);
        return ResponseEntity.ok(atualizar);
    }

    @GetMapping
    @Operation(
            summary = "Listar clientes",
            description = "Retorna a lista de todos os clientes cadastrados no sistema."
    )
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos() {
        var listar = clienteService.listarTodos();
        return ResponseEntity.ok(listar);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar cliente por ID",
            description = "Retorna os dados de um cliente específico a partir do seu identificador."
    )
    public ResponseEntity<List<ClienteResponseDTO>> listarCliente(@PathVariable Long id) {
        var listarCliente = clienteService.listarPorCliente(id);
        return ResponseEntity.ok(listarCliente);
    }

    @Operation(
            summary = "Excluir cliente",
            description = "Remove um cliente do sistema a partir do seu identificador."
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> excluir(@PathVariable Long id) {
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
