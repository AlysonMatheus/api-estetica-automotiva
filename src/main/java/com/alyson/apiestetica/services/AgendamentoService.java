package com.alyson.apiestetica.services;


import com.alyson.apiestetica.entity.*;
import com.alyson.apiestetica.entity.request.AgendamentoRequestDTO;
import com.alyson.apiestetica.entity.response.AgendamentoResponseDTO;
import com.alyson.apiestetica.enums.StatusAgendamento;
import com.alyson.apiestetica.repository.AgendamentoRepository;
import com.alyson.apiestetica.repository.CarroRepository;
import com.alyson.apiestetica.repository.ServicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ServicoRepository servicoRepository;
    private final CarroRepository carroRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository, ServicoRepository servicoRepository, CarroRepository carroRepository) {

        this.agendamentoRepository = agendamentoRepository;
        this.servicoRepository = servicoRepository;
        this.carroRepository = carroRepository;
    }

    public AgendamentoResponseDTO cadastrar(AgendamentoRequestDTO agendamentoDTO) {

        Agendamento agendamento = agendamentoRepository.findById(agendamentoDTO.idCarro()).orElseThrow(()-> new RuntimeException("Carro não encontrado"));
        agendamentoRepository.save(agendamento);
        return new AgendamentoResponseDTO(agendamento);
    }

    public AgendamentoResponseDTO editar(Long id, AgendamentoRequestDTO dto) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Agendamento" + id + "não encontrado"));
        agendamento.AtualizarDados(dto);
        agendamentoRepository.save(agendamento);
        return new AgendamentoResponseDTO(agendamento);

    }

    public void excluir(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Agendamento" + id + "não encontrado"));
        agendamentoRepository.delete(agendamento);

    }

    public AgendamentoResponseDTO buscarAgendamento(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        return new AgendamentoResponseDTO(agendamento);
    }

    public List<AgendamentoResponseDTO> listartodos() {
        return agendamentoRepository.findAll().stream().map(AgendamentoResponseDTO::new).toList();
    }

    public AgendamentoResponseDTO alterarStatus(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Agendamento" + id + "não encontrado"));
        if (agendamento.getStatus() == StatusAgendamento.AGENDADO) {
            agendamento.setStatus(StatusAgendamento.EM_ANDAMENTO);
        }
        var salvar = agendamentoRepository.save(agendamento);
        return new AgendamentoResponseDTO(salvar);
    }
@Transactional
    public AgendamentoResponseDTO vincularServicos( AgendamentoRequestDTO dto) {

        Carro carro = carroRepository.findById(dto.idCarro()).orElseThrow(() -> new RuntimeException("Carro não encontrado"));

        Agendamento agendamento = new Agendamento(dto, carro);
        agendamentoRepository.save(agendamento);

        dto.servicos().forEach(idServico -> {
            Servico servico = servicoRepository.findById(idServico).orElseThrow(() -> new RuntimeException("Servico não encontrado"));
            AgendamentoServicoId agendamentoServicoId = new AgendamentoServicoId(agendamento.getIdAgendamento(), servico.getIdServico());
            AgendamentoServico agendamentoServico = new AgendamentoServico(agendamentoServicoId, agendamento, servico, servico.getPreco());
            agendamento.getServicos().add(agendamentoServico);
        });

        agendamentoRepository.save(agendamento);

        return new AgendamentoResponseDTO(agendamento);
    }


    }


