package br.com.serratec.atividade2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.atividade2.dto.LancamentoVendasResponseDTO;
import br.com.serratec.atividade2.model.LancamentoVendas;
import br.com.serratec.atividade2.repository.LancamentoRepository;

@Service
public class LancamentoService {
    @Autowired
    private LancamentoRepository repository;

    public LancamentoVendasResponseDTO inserirLancamento(LancamentoVendas lancamento) {
        repository.save(lancamento);
        return new LancamentoVendasResponseDTO(lancamento.getData(), lancamento.getValor(), lancamento.getVendedor().getNome());
    }

    public Optional<LancamentoVendas> listarPorId(Long id) {
        return repository.findById(id);
    }
    
}
