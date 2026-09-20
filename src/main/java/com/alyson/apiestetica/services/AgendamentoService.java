package com.alyson.apiestetica.services;


import com.alyson.apiestetica.entity.*;
import com.alyson.apiestetica.entity.request.AgendamentoRequestDTO;
import com.alyson.apiestetica.entity.response.AgendamentoResponseDTO;
import com.alyson.apiestetica.enums.StatusAgendamento;
import com.alyson.apiestetica.repository.AgendamentoRepository;
import com.alyson.apiestetica.repository.VeiculoRepository;
import com.alyson.apiestetica.repository.ServicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ServicoRepository servicoRepository;
    private final VeiculoRepository veiculoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository, ServicoRepository servicoRepository, VeiculoRepository veiculoRepository) {

        this.agendamentoRepository = agendamentoRepository;
        this.servicoRepository = servicoRepository;
        this.veiculoRepository = veiculoRepository;
    }

    @Transactional
    public AgendamentoResponseDTO cadastrar(
            AgendamentoRequestDTO dto
    ) {
        if (!dto.horarioFinal().isAfter(dto.horarioInicio())) {
            throw new RuntimeException("O horário final deve ser posterior ao horário inicial");
        }
        boolean existeConflito =
                agendamentoRepository.existsByHorarioInicioLessThanAndHorarioFinalGreaterThan(
                        dto.horarioFinal(),
                        dto.horarioInicio()
                );

        if (existeConflito) {
            throw new RuntimeException("Já existe um agendamento neste horário");
        }
        Veiculo veiculo = veiculoRepository.findById(dto.idVeiculo()).orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        Agendamento agendamento = new Agendamento(dto, veiculo);

        agendamentoRepository.save(agendamento);


        dto.servicos().forEach(item -> {
            Servico servico = servicoRepository.findById(item.idServico()).orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
            AgendamentoServicoId id = new AgendamentoServicoId(agendamento.getIdAgendamento(), servico.getIdServico());
            AgendamentoServico agendamentoServico = new AgendamentoServico(id, agendamento, servico, item.precoCobrado());
            agendamento.getServicos().add(agendamentoServico);

        });
        agendamentoRepository.save(agendamento);

        return new AgendamentoResponseDTO(agendamento);
    }

    @Transactional
    public AgendamentoResponseDTO editar(Long id, AgendamentoRequestDTO dto) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Agendamento " + id + " não encontrado"));
        Veiculo veiculo = veiculoRepository.findById(dto.idVeiculo()).orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        if (!dto.horarioFinal().isAfter(dto.horarioInicio())) {
            throw new RuntimeException("O horário final deve ser posterior ao horário inicial");
        }

        agendamento.atualizarDados(dto);
        agendamento.setVeiculo(veiculo);
        agendamento.getServicos().clear();

        dto.servicos().forEach(item -> {
            Servico servico = servicoRepository.findById(item.idServico()).orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
            AgendamentoServicoId servicoId = new AgendamentoServicoId(agendamento.getIdAgendamento(), servico.getIdServico());
            AgendamentoServico agendamentoServico = new AgendamentoServico(servicoId, agendamento, servico, item.precoCobrado());
            agendamento.getServicos().add(agendamentoServico);
        });

        Agendamento salvo = agendamentoRepository.save(agendamento);
        return new AgendamentoResponseDTO(salvo);
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

    public AgendamentoResponseDTO alterarStatus(Long id, StatusAgendamento novoStatus) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Agendamento " + id + " não encontrado"));

        StatusAgendamento statusAtual = agendamento.getStatus();

        boolean transicaoValida =
                statusAtual == StatusAgendamento.AGENDADO && (novoStatus == StatusAgendamento.CONFIRMADO || novoStatus == StatusAgendamento.CANCELADO) ||
                        statusAtual == StatusAgendamento.CONFIRMADO && (novoStatus == StatusAgendamento.EM_ANDAMENTO || novoStatus == StatusAgendamento.CANCELADO) ||
                        statusAtual == StatusAgendamento.EM_ANDAMENTO && (novoStatus == StatusAgendamento.FINALIZADO || novoStatus == StatusAgendamento.CANCELADO);

        if (!transicaoValida) {
            throw new RuntimeException("Não é possível alterar o status de " + statusAtual + " para " + novoStatus);
        }

        agendamento.setStatus(novoStatus);
        Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);

        return new AgendamentoResponseDTO(agendamentoSalvo);
    }


}


