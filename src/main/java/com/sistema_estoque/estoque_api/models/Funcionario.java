package com.sistema_estoque.estoque_api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sistema_estoque.estoque_api.models.enums.CargosEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "funcionarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "cpf", unique = true, length = 11, nullable = false)
    private String cpf;
    @Column(name = "cargo", nullable = false)
    @Enumerated(EnumType.STRING)
    private CargosEnum cargo;
    @Column(name = "codigo_funcionario", nullable = false, unique = true)
    private String codigoF;
    private LocalDateTime criadoEm = LocalDateTime.now();
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm = LocalDateTime.now();
    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Movimentacao> movimentacoes = new ArrayList<>();
}

