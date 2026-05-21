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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {
    @Autowired
    private LancamentoService service;

    @Operation(summary = "Lançamento vendas", description = "A resposta retorna dados de vendas")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", 
			content = {@Content(schema = @Schema(implementation = LancamentoVendas.class), mediaType = "application/json")},
			description = "Cadastra vendas"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LancamentoVendasResponseDTO inserirLancamento(@Valid @RequestBody LancamentoVendas lancamento) {
        return service.inserirLancamento(lancamento);
    }

    @Operation(summary = "Lista todas as vendas", description = "A resposta lista os dados das vendas por dataVenda, valorVenda e nomeVendedor.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", 
			content = {@Content(schema = @Schema(implementation = LancamentoVendas.class), mediaType = "application/json")},
			description = "Retorna todas as vendas"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })


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