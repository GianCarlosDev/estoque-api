package com.sistema_estoque.estoque_api.controller;

import com.sistema_estoque.estoque_api.DTOs.MovimentacaoDTO;
import com.sistema_estoque.estoque_api.service.MovimentacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {
    private final MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }
    @PostMapping
    public ResponseEntity<?> saveMov(@RequestBody MovimentacaoDTO dto) {
        try {
            return new ResponseEntity<>(movimentacaoService.saveMovimentacao(dto), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
