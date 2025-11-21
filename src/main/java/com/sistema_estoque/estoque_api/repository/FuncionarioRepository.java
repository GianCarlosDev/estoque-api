package com.sistema_estoque.estoque_api.repository;

import com.sistema_estoque.estoque_api.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByCodigoF(String codigoF);
}
