package com.sistema_estoque.estoque_api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produtos", uniqueConstraints = {
        @UniqueConstraint(name = "uk_nome_descricao", columnNames = {"nome", "descricao"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    @Column(name = "descricao", length = 250, nullable = false)
    private String descricao;
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    @Column(name = "quantidade_minima", nullable = false)
    private Integer quantidadeMinima;
    @Column(name = "preco", precision = 7, scale = 2, nullable = false)
    private BigDecimal preco;
    @Column(name = "codigo_produto", nullable = false, unique = true)
    private String codigoP;
    @Column(name = "criado_em")
    private LocalDateTime criadoEm = LocalDateTime.now();
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm = LocalDateTime.now();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Movimentacao> movimentacaos = new ArrayList<>();
}

