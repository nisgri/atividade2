package br.com.serratec.atividade2.dto;

import java.time.LocalDateTime;

public record LancamentoVendasResponseDTO(LocalDateTime dataVenda, Double valorVenda, String nomeVendedor) {

}
