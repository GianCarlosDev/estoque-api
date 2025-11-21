package com.sistema_estoque.estoque_api.controller;

import com.sistema_estoque.estoque_api.DTOs.FuncionarioDTO;
import com.sistema_estoque.estoque_api.service.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }
    @PostMapping
    public ResponseEntity<?> saveFuncionario(@RequestBody FuncionarioDTO dto) {
        try {
            return new ResponseEntity<>(funcionarioService.saveFuncionario(dto), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllFuncionarios() {
        try {
            return new ResponseEntity<>(funcionarioService.listFuncionario(), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{codigoF}")
    public ResponseEntity<?> getByCodigoF(@PathVariable String codigoF) {
        try {
            return new ResponseEntity<>(funcionarioService.getFuncionarioCodigoF(codigoF), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{codigoF}")
    public ResponseEntity<?> updateFuncionario(@PathVariable String codigoF, @RequestBody FuncionarioDTO dto) {
        try {
            return new ResponseEntity<>(funcionarioService.updateFuncionario(codigoF,dto), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{codigoF}")
    public ResponseEntity<?> delete(@PathVariable String codigoF) {
        try {
            funcionarioService.deleteFuncionario(codigoF);
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
