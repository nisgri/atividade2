package br.com.serratec.atividade2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.atividade2.model.LancamentoVendas;

public interface LancamentoRepository extends JpaRepository<LancamentoVendas, Long>{
    
}
