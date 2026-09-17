package com.alyson.apiestetica.services;


import com.alyson.apiestetica.entity.Veiculo;
import com.alyson.apiestetica.entity.Cliente;
import com.alyson.apiestetica.entity.request.VeiculoRequestDTO;
import com.alyson.apiestetica.entity.response.VeiculoResponseDTO;
import com.alyson.apiestetica.repository.VeiculoRepository;
import com.alyson.apiestetica.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {


    private final VeiculoRepository veiculoRepository;
    private final ClienteRepository clienteRepository;

    public VeiculoService(VeiculoRepository veiculoRepository, ClienteRepository clienteRepository) {
        this.veiculoRepository = veiculoRepository;
        this.clienteRepository = clienteRepository;
    }

    public VeiculoResponseDTO cadastrar(VeiculoRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.idCliente()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Veiculo veiculo = new Veiculo(dto, cliente);

        veiculoRepository.save(veiculo);
        return new VeiculoResponseDTO(veiculo);
    }

    public VeiculoResponseDTO atualizar(Long id, VeiculoRequestDTO dto) {
        Veiculo veiculo = veiculoRepository.findById(id).orElseThrow(() -> new RuntimeException("Veiculo " + id + "nao encontrado"));
        veiculo.AtualizarDados(dto);
        veiculoRepository.save(veiculo);
        return new VeiculoResponseDTO(veiculo);
    }

    public VeiculoResponseDTO buscarVeiculo(Long id) {
        Veiculo veiculo = veiculoRepository.findById(id).orElseThrow(() -> new RuntimeException("Veiculo " + id + "nao encontrado"));
        return new VeiculoResponseDTO(veiculo);

    }

//    public List<CarroResponseDTO> buscarCarroCliente(Long id) {
//        List<Carro> carros = carroRepository.findByClienteId(id);
//        return carros.stream().map(CarroResponseDTO::new).toList();
//    }

    public void excluir(Long id) {
        Veiculo veiculo = veiculoRepository.findById(id).orElseThrow(() -> new RuntimeException("Carro " + id + "nao encontrado"));
        veiculoRepository.delete(veiculo);
    }

}
