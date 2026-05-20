package br.com.serratec.atividade2.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class VendedorProfissional extends Vendedor {
    @NotBlank(message= "Preencha o CNPJ.")
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
}
