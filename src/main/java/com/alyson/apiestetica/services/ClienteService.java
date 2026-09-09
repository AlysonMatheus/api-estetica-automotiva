package com.alyson.apiestetica.services;


import com.alyson.apiestetica.entity.Cliente;
import com.alyson.apiestetica.entity.request.ClienteRequestDTO;
import com.alyson.apiestetica.entity.response.ClienteResponseDTO;
import com.alyson.apiestetica.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponseDTO cadastrar(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente(dto);
        clienteRepository.save(cliente);
        return new ClienteResponseDTO(cliente);
    }



    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não econtrado"));
        cliente.AtualizarDados(dto);
        clienteRepository.save(cliente);
        return new ClienteResponseDTO(cliente);
    }
    public List<ClienteResponseDTO> listarTodos(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(cliente -> new ClienteResponseDTO(cliente)).collect(Collectors.toList());
    }

    public List<ClienteResponseDTO>listarPorCliente(Long id){
        Cliente clientes = clienteRepository.findById(id).orElseThrow(()->new RuntimeException("Cliente não econtrado"));
      return List.of(new ClienteResponseDTO(clientes));

    }
    public void excluir(Long id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()-> new RuntimeException("Cliente não econtrado"));
        clienteRepository.delete(cliente);
    }
}
