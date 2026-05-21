package br.com.serratec.atividade2.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name= "tipo_vendedor", discriminatorType=DiscriminatorType.STRING)
public class Vendedor {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Long id;

    @NotBlank(message= "Preencha o nome.")
    @Size(max= 50, message= "Limite de caracteres: 50.")
    protected String nome;

    @NotBlank(message= "Preencha o e-mail.")
    @Email(message= "Preencha um e-mail válido.")
    protected String email;

    @NotNull(message= "Preencha o salário.")
    @DecimalMin(value= "1600", message= "Mínimo: R$1.600,00.")
    protected Double salario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

}
