package com.sistema_estoque.estoque_api.repository;

import com.sistema_estoque.estoque_api.models.Funcionario;
import com.sistema_estoque.estoque_api.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByCodigoP(String codigoP);
}
