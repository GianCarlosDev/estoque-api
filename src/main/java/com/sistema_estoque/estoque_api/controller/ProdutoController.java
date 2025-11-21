package com.sistema_estoque.estoque_api.controller;

import com.sistema_estoque.estoque_api.DTOs.ProdutoDTO;
import com.sistema_estoque.estoque_api.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<?> saveProduto(@RequestBody ProdutoDTO dto) {
        try {
            return new ResponseEntity<>(produtoService.saveProduto(dto),HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllProdutos() {
        try {
            return new ResponseEntity<>(produtoService.listProduto(), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{codigoP}")
    public ResponseEntity<?> getByProdutoId(@PathVariable String codigoP) {
        try {
            return new ResponseEntity<>(produtoService.getByProdutoId(codigoP), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{codigoP}")
    public ResponseEntity<?> updateProduto(@PathVariable String codigoP, @RequestBody ProdutoDTO dto) {
        try {
            return new ResponseEntity<>(produtoService.updateProduto(codigoP,dto), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{codigoP}")
    public ResponseEntity<?> deleteProduto(@PathVariable String codigoP) {
        try {
        produtoService.deleteProduto(codigoP);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/alerta")
    public ResponseEntity<?> listProdutoAlerta() {
        try {
            return new ResponseEntity<>(produtoService.getAllProdutoAlerta(), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
