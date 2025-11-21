package com.sistema_estoque.estoque_api.service;

import com.sistema_estoque.estoque_api.DTOs.MovimentacaoDTO;
import com.sistema_estoque.estoque_api.models.Funcionario;
import com.sistema_estoque.estoque_api.models.Movimentacao;
import com.sistema_estoque.estoque_api.models.Produto;
import com.sistema_estoque.estoque_api.models.enums.CargosEnum;
import com.sistema_estoque.estoque_api.models.enums.TipoMovimentacaoEnum;
import com.sistema_estoque.estoque_api.repository.FuncionarioRepository;
import com.sistema_estoque.estoque_api.repository.MovimentacaoRepository;
import com.sistema_estoque.estoque_api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final ProdutoRepository produtoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, ProdutoRepository produtoRepository, FuncionarioRepository funcionarioRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.produtoRepository = produtoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public MovimentacaoDTO saveMovimentacao(MovimentacaoDTO dto) {
        try {
            Produto produto = produtoRepository.findByCodigoP(dto.getCodigoP()).
                    orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            Funcionario funcionario = funcionarioRepository.findByCodigoF(dto.getCodigoF()).
                    orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));

            if (dto.getTipo() == TipoMovimentacaoEnum.SAIDA) {
                if (produto.getQuantidade() < dto.getQuantidade()) {
                    throw new RuntimeException("Estoque insuficiente para realizar a saída");
                }
                produto.setQuantidade(produto.getQuantidade() - dto.getQuantidade());
                produtoRepository.save(produto);
                verificarEstoqueBaixo(produto);
            } else {
                produto.setQuantidade(produto.getQuantidade() + dto.getQuantidade());
            }

            if (dto.getTipo() == TipoMovimentacaoEnum.ENTRADA &&
                    funcionario.getCargo() != CargosEnum.REPOSITOR) {
                throw new RuntimeException("Apenas repositores podem registrar ENTRADA de produtos.");
            }
            if (dto.getTipo() == TipoMovimentacaoEnum.SAIDA &&
                    funcionario.getCargo() != CargosEnum.VENDEDOR) {
                throw new RuntimeException("Apenas vendedores podem registrar SAÍDA de produtos.");
            }
            Movimentacao entity = new Movimentacao();
            entity.setProduto(produto);
            entity.setFuncionario(funcionario);
            entity.setQuantidade(dto.getQuantidade());
            entity.setTipo(dto.getTipo());
            entity.setData(dto.getData());
            movimentacaoRepository.save(entity);
            dto.setAlerta(verificarEstoqueBaixo(produto));
            produtoRepository.save(produto);
            dto.setId(entity.getId());
            return dto;
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro, Movimentação inválida");
        }
    }

    private String verificarEstoqueBaixo(Produto produto) {
        if (produto.getQuantidade() <= produto.getQuantidadeMinima()) {
            return "O produto " + produto.getNome() +
                    " está com baixo estoque! Estoque atual: " +
                    produto.getQuantidade();

        }
        return "";
    }

}

