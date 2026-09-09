package com.alyson.apiestetica.services;


import com.alyson.apiestetica.entity.Carro;
import com.alyson.apiestetica.entity.Cliente;
import com.alyson.apiestetica.entity.request.CarroRequestDTO;
import com.alyson.apiestetica.entity.response.CarroResponseDTO;
import com.alyson.apiestetica.entity.response.ClienteResponseDTO;
import com.alyson.apiestetica.repository.CarroRepository;
import com.alyson.apiestetica.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {


    private final CarroRepository carroRepository;
    private final ClienteRepository clienteRepository;

    public CarroService(CarroRepository carroRepository, ClienteRepository clienteRepository) {
        this.carroRepository = carroRepository;
        this.clienteRepository = clienteRepository;
    }

    public CarroResponseDTO cadastrar(Long id, CarroRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Carro carro = new Carro(dto, cliente);

        carroRepository.save(carro);
        return new CarroResponseDTO(carro);
    }

    public CarroResponseDTO atualizarCarro(Long id, CarroRequestDTO dto) {
        Carro carro = carroRepository.findById(id).orElseThrow(() -> new RuntimeException("Carro " + id + "nao encontrado"));
        carro.AtualizarDados(dto);
        carroRepository.save(carro);
        return new CarroResponseDTO(carro);
    }

    public CarroResponseDTO buscarCarro(Long id) {
        Carro carro = carroRepository.findById(id).orElseThrow(() -> new RuntimeException("Carro " + id + "nao encontrado"));
        return new CarroResponseDTO(carro);

    }

    public List<CarroResponseDTO> buscarCarroCliente(Long id) {
        List<Carro> carros = carroRepository.findByClienteId(id);
        return carros.stream().map(CarroResponseDTO::new).toList();
    }

    public void excluir(Long id) {
        Carro carro = carroRepository.findById(id).orElseThrow(() -> new RuntimeException("Carro " + id + "nao encontrado"));
        carroRepository.delete(carro);
    }

}
