package br.com.serratec.atividade2.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class VendedorAutonomo extends Vendedor {
    @NotNull(message= "Preencha a comissão.")
    private Double comissao;

    public Double getComissao() {
        return comissao;
    }

    public void setComissao(Double comissao) {
        this.comissao = comissao;
    }
    
}
