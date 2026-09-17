package com.alyson.apiestetica.controller;

import com.alyson.apiestetica.entity.request.VeiculoRequestDTO;
import com.alyson.apiestetica.entity.response.VeiculoResponseDTO;
import com.alyson.apiestetica.services.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/veiculo")
@Tag(
        name = "Veículos",
        description = "Endpoints para gerenciamento dos veículos vinculados aos clientes"
)
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @Operation(
            summary = "Cadastrar veículo",
            description = "Cadastra um novo veículo e o vincula a um cliente já existente no sistema."
    )

    @PostMapping("/cadastrar")
    public ResponseEntity<VeiculoResponseDTO> cadastrar(@RequestBody VeiculoRequestDTO dto) {
        var cadastrar = veiculoService.cadastrar(dto);
        return ResponseEntity.ok(cadastrar);

    }
    @Operation(
            summary = "Atualizar veículo",
            description = "Atualiza os dados de um veículo existente, como modelo, marca, placa, cor e ano."
    )
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<VeiculoResponseDTO> atualizar(@PathVariable Long id, @RequestBody VeiculoRequestDTO dto) {
        var atualizar = veiculoService.atualizar(id, dto);
        return ResponseEntity.ok(atualizar);
    }

    @Operation(
            summary = "Buscar veículo por ID",
            description = "Retorna os dados de um veículo específico a partir do seu identificador."
    )

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> buscarCarro(@PathVariable Long id) {
        var buscar = veiculoService.buscarVeiculo(id);
        return ResponseEntity.ok(buscar);

    }

    //    @GetMapping("cliente/{ìd}")
//    public ResponseEntity<List<CarroResponseDTO>> buscarCarroporCliente(@PathVariable Long id) {
//        var buscarPorCliente = carroService.buscarCarroCliente(id);
//        return ResponseEntity.ok(buscarPorCliente);
//    }
    @Operation(
            summary = "Excluir veículo",
            description = "Remove um veículo cadastrado no sistema a partir do seu identificador."
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        veiculoService.excluir(id);
        return ResponseEntity.noContent().build();


    }
}
