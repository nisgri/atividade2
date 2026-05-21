package br.com.serratec.atividade2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.atividade2.model.*;
import jakarta.validation.Valid;

import java.util.*;

import br.com.serratec.atividade2.dto.LancamentoVendasResponseDTO;
import br.com.serratec.atividade2.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {
    @Autowired
    private LancamentoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LancamentoVendasResponseDTO inserirLancamento(@Valid @RequestBody LancamentoVendas lancamento) {
        return service.inserirLancamento(lancamento);
    }

    @GetMapping("{id}")
    public ResponseEntity<LancamentoVendasResponseDTO> listarPorId(@PathVariable Long id) {
        Optional<LancamentoVendas> lancamento= service.listarPorId(id);
        if(lancamento.isPresent()) {
            LancamentoVendasResponseDTO dto= new LancamentoVendasResponseDTO(lancamento.get().getData(), lancamento.get().getValor(), lancamento.get().getVendedor().getNome());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }
}