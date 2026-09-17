package com.alyson.apiestetica.entity;

import com.alyson.apiestetica.entity.request.ClienteRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "rua")
    private String rua;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "numero")
    private String numero;

    @OneToMany(
            mappedBy = "cliente",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Veiculo> veiculos = new ArrayList<>();

    public Cliente(ClienteRequestDTO dto) {
        this.nome = dto.nome();
        this.telefone = dto.telefone();
        this.rua = dto.rua();
        this.bairro = dto.bairro();
        this.numero = dto.numero();

    }

    public void AtualizarDados(ClienteRequestDTO dto) {
        this.nome = dto.nome();
        this.telefone = dto.telefone();
        this.rua = dto.rua();
        this.bairro = dto.bairro();
        this.numero = dto.numero();

    }
}