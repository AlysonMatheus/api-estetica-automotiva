package com.alyson.apiestetica.entity;

import com.alyson.apiestetica.entity.request.VeiculoRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carro")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_veiculo")
    private Long idVeiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "marca")
    private String marca;

    @Column(name = "placa", nullable = false, unique = true)
    private String placa;

    @Column(name = "cor")
    private String cor;

    @Column(name = "ano")
    private Integer ano;


    public Veiculo(VeiculoRequestDTO dto, Cliente cliente){
        this.cliente = cliente;
        this.modelo = dto.modelo();
        this.marca = dto.marca();
        this.placa = dto.marca();
        this.cor = dto.cor();
        this.ano = dto.ano();
    }
    public void AtualizarDados(VeiculoRequestDTO dto){
        this.modelo = dto.modelo();
        this.marca = dto.marca();
        this.placa = dto.marca();
        this.cor = dto.cor();
        this.ano = dto.ano();
    }
}