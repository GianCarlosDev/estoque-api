package com.sistema_estoque.estoque_api.service;

import com.sistema_estoque.estoque_api.DTOs.ProdutoDTO;
import com.sistema_estoque.estoque_api.models.Produto;
import com.sistema_estoque.estoque_api.repository.ProdutoRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoDTO saveProduto(ProdutoDTO dto) {
        try {
            Produto entity = new Produto();
            entity.setNome(dto.getNome());
            entity.setDescricao(dto.getDescricao());
            entity.setQuantidade(dto.getQuantidade());
            entity.setQuantidadeMinima(dto.getQuantidadeMinima());
            entity.setPreco(dto.getPreco());
            entity.setCodigoP(gerarCodigoP());
            entity.setCriadoEm(dto.getCriadoEm());
            produtoRepository.save(entity);
            dto.setId(entity.getId());
            dto.setCodigoP(entity.getCodigoP());
            return dto;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erro, nome e descrição iguais, ou argumentos nulos: " + e.getMessage());
        }
    }
    private String gerarCodigoP() {
        return "P" + RandomStringUtils.randomAlphanumeric(6).toUpperCase();
    }
    public Stream<ProdutoDTO> listProduto() {
        List<Produto> produtos = produtoRepository.findAll();
        if (produtos.isEmpty()){
            throw new RuntimeException("lista está vazia: " + HttpStatus.NOT_FOUND);
        }
        return produtos.stream().map(produto -> new ProdutoDTO(
                produto.getId(), produto.getNome(), produto.getDescricao(), produto.getQuantidade(),
                produto.getQuantidadeMinima(),produto.getPreco(), produto.getCodigoP(),
                produto.getCriadoEm(), produto.getAtualizadoEm(), verificarEstoqueBaixo(produto)
        ));
    }
    public ProdutoDTO getByProdutoId(String codigoP) {
        Produto entity = produtoRepository.findByCodigoP(codigoP).
            orElseThrow(() -> new RuntimeException("código do produto não encontrado: " + codigoP));
        return new ProdutoDTO(
                entity.getId(), entity.getNome(), entity.getDescricao(), entity.getQuantidade(),
                entity.getQuantidadeMinima(), entity.getPreco(), entity.getCodigoP(),
                entity.getCriadoEm(), entity.getAtualizadoEm(), verificarEstoqueBaixo(entity)
        );
    }
    public ProdutoDTO updateProduto(String codigoP, ProdutoDTO dto) {
        try {
            Produto entity = produtoRepository.findByCodigoP(codigoP).
                    orElseThrow(() -> new RuntimeException("código do produto não encontrado: " + codigoP));
            entity.setNome(dto.getNome());
            entity.setDescricao(dto.getDescricao());
            entity.setQuantidade(dto.getQuantidade());
            entity.setQuantidadeMinima(dto.getQuantidadeMinima());
            entity.setPreco(dto.getPreco());
            entity.setAtualizadoEm(dto.getAtualizadoEm());
            dto.setId(entity.getId());
            dto.setCodigoP(entity.getCodigoP());
            produtoRepository.save(entity);
            return dto;
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao atualizar produto: " + e.getMessage());
        }
    }
    public void deleteProduto(String codigoP) {
       Produto entity = produtoRepository.findByCodigoP(codigoP).
               orElseThrow(() -> new RuntimeException("Erro, código não encontrado: " + codigoP));
        produtoRepository.delete(entity);
    }
    private String verificarEstoqueBaixo(Produto produto) {
        if (produto.getQuantidade() <= produto.getQuantidadeMinima()) {
            return "O produto " + produto.getNome() +
                    " está com baixo estoque! Estoque atual: " +
                    produto.getQuantidade();

        }
        return "";
    }
    public Stream<ProdutoDTO> getAllProdutoAlerta() {
        List<Produto> produtos = produtoRepository.findAll();
        if (produtos.isEmpty()){
            throw new RuntimeException("Não tem produtos com alerta de estoque");
        }
        return produtos.stream().filter(p -> p.getQuantidade() <= p.getQuantidadeMinima()).
                map(produto -> new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getQuantidade(),
                        produto.getQuantidadeMinima(), produto.getPreco(), produto.getCodigoP(), produto.getCriadoEm(), produto.getAtualizadoEm(),
                        verificarEstoqueBaixo(produto)));
    }
}
