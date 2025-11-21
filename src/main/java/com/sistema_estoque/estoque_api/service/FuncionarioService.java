package com.sistema_estoque.estoque_api.service;

import com.sistema_estoque.estoque_api.DTOs.FuncionarioDTO;
import com.sistema_estoque.estoque_api.models.Funcionario;
import com.sistema_estoque.estoque_api.repository.FuncionarioRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public FuncionarioDTO saveFuncionario(FuncionarioDTO dto) {
        try {
            Funcionario entity = new Funcionario();
            entity.setNome(dto.getNome());
            entity.setCpf(dto.getCpf());
            entity.setCargo(dto.getCargo());
            entity.setCodigoF(gerarCodigoF(entity));
            entity.setCriadoEm(dto.getCriadoEm());
            funcionarioRepository.save(entity);
            dto.setId(entity.getId());
            dto.setCodigoF(entity.getCodigoF());
            return dto;
        }catch (RuntimeException e){
            throw new RuntimeException("Erro ao salvar funcionario: " + e.getMessage());
        }
    }
    private String gerarCodigoF(Funcionario funcionario) {

        if (funcionario.getCargo() == null) {
            throw new RuntimeException("Cargo não pode ser nulo para gerar o código");
        }
        String prefixo = switch (funcionario.getCargo()) {
            case VENDEDOR -> "FV";
            case REPOSITOR -> "FR";
            default -> "VG";
        };
        return prefixo + RandomStringUtils.randomAlphanumeric(8).toUpperCase();
    }
    public Stream<FuncionarioDTO> listFuncionario() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        if (funcionarios.isEmpty()){
            throw new RuntimeException("A lista está vazia: " + HttpStatus.NOT_FOUND);
        }
        return funcionarios.stream().map(
                funcionario -> new FuncionarioDTO(
                        funcionario.getId(), funcionario.getNome(), funcionario.getCpf(), funcionario.getCargo(),
                        funcionario.getCodigoF(), funcionario.getCriadoEm(), funcionario.getAtualizadoEm()
                ));
    }
    public FuncionarioDTO getFuncionarioCodigoF(String codigoF) {
        Funcionario entity = funcionarioRepository.findByCodigoF(codigoF).
                orElseThrow(() -> new RuntimeException("Erro, código não encontrado: " + codigoF));
        return new FuncionarioDTO(
                entity.getId(), entity.getNome(),entity.getCpf(), entity.getCargo(),
                entity.getCodigoF(), entity.getCriadoEm(), entity.getAtualizadoEm());
    }
    public FuncionarioDTO updateFuncionario(String codigoF, FuncionarioDTO dto) {
        try {
            Funcionario entity = funcionarioRepository.findByCodigoF(codigoF).
                    orElseThrow(() -> new RuntimeException("Erro, código não encontrado: " + codigoF));
            entity.setNome(dto.getNome());
            entity.setCpf(dto.getCpf());
            entity.setCargo(dto.getCargo());
            entity.setAtualizadoEm(dto.getAtualizadoEm());
            funcionarioRepository.save(entity);
            dto.setId(entity.getId());
            return dto;
        } catch (RuntimeException e) {
            throw new RuntimeException("erro em atualizar o funcionario: " + e.getMessage());
        }
    }
    public void deleteFuncionario(String codigoF) {
        Funcionario entity = funcionarioRepository.findByCodigoF(codigoF).
                orElseThrow(() -> new RuntimeException("Erro, código não encontrado: " + codigoF));
        funcionarioRepository.delete(entity);
    }
}
