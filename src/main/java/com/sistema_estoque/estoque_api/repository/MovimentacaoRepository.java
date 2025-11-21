package com.sistema_estoque.estoque_api.repository;

import com.sistema_estoque.estoque_api.models.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
}
