package br.com.serratec.atividade2.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;

@Entity
public class LancamentoVendas {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull(message= "Preencha a data.")
    private LocalDateTime data;

    @NotNull(message= "Preencha o valor.")
    private Double valor;

    @ManyToOne
    @JoinColumn(name= "id_vendedor")
    @NotNull(message= "Preencha o vendedor.")
    private Vendedor vendedor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    @PrePersist
    public void prePersistData() {
        data= LocalDateTime.now();
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
}
