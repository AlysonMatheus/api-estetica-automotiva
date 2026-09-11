package com.alyson.apiestetica.controller;

import com.alyson.apiestetica.entity.request.ServicoRequestDTO;
import com.alyson.apiestetica.entity.response.ServicoResponseDTO;
import com.alyson.apiestetica.services.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class ClienteController {
    @Autowired
    private ServicoService servicoService;


    @PostMapping("/cadastrar")
    public ResponseEntity<ServicoResponseDTO>cadastrar(@RequestBody ServicoRequestDTO dto){
        var cadastro = servicoService.cadastrar(dto);
        return ResponseEntity.ok(cadastro);
    }
    @PutMapping("/atualizar")
    public ResponseEntity<ServicoResponseDTO>atualizar(Long id,ServicoRequestDTO dto){
          var atualizar = servicoService.atualizar(id,dto)  ;
          return ResponseEntity.ok(atualizar);

    }
    @GetMapping()
    public ResponseEntity<ServicoResponseDTO>buscarServico(Long id){
        var buscarServico = servicoService.buscarServico(id);
        return ResponseEntity.ok(buscarServico);

    }
    public ResponseEntity<List<ServicoResponseDTO>> listarServico(){
        var listarServico = listarServico();
        return ResponseEntity.ok(listarServico);

    }



}
