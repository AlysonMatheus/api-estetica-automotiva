package com.alyson.apiestetica.services;


import com.alyson.apiestetica.entity.Agendamento;
import com.alyson.apiestetica.entity.Servico;
import com.alyson.apiestetica.entity.request.ServicoRequestDTO;
import com.alyson.apiestetica.entity.response.ServicoResponseDTO;
import com.alyson.apiestetica.repository.AgendamentoRepository;
import com.alyson.apiestetica.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoService {
    private final ServicoRepository servicoRepository;
    private final AgendamentoRepository agendamentoRepository;

    public ServicoService(ServicoRepository servicoRepository, AgendamentoRepository agendamentoRepository) {
        this.servicoRepository = servicoRepository;
        this.agendamentoRepository = agendamentoRepository;
    }

    public ServicoResponseDTO cadastrar( ServicoRequestDTO dto) {
        Servico servico = new Servico(dto);
        servicoRepository.save(servico);

        return new ServicoResponseDTO(servico);

    }

    public ServicoResponseDTO buscarServico(Long id) {
        Servico servico = servicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Servico não encontrado"));
        return new ServicoResponseDTO(servico);

    }

    public List<ServicoResponseDTO> listarServicos() {
        List<Servico> servico = servicoRepository.findAll();
        return servico.stream().map(x -> new ServicoResponseDTO(x)).collect(Collectors.toList());
    }

    public ServicoResponseDTO atualizar(Long id, ServicoRequestDTO dto) {
        Servico servico = servicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Servico não encontrado"));
        servico.AtualizarDados(dto);
        servicoRepository.save(servico);
        return new ServicoResponseDTO(servico);
    }

    public void ativarServico(Long id) {
        Servico servico = servicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Servico não encontrado"));
        servico.setAtivo(true);
        servicoRepository.save(servico);
    }

    public void desativarServico(Long id) {
        Servico servico = servicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Servico não encontrado"));
        servico.setAtivo(false);
        servicoRepository.save(servico);
    }
}
