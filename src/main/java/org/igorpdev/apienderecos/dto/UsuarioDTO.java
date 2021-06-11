package org.igorpdev.apienderecos.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.igorpdev.apienderecos.model.Endereco;

public class UsuarioDTO {
    
    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String cpf;

    @NotNull
    @NotBlank
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date nascimento;

    private List<Endereco> enderecos;

    public List<Endereco> getEnderecos() {
        return this.enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getNascimento() {
        return this.nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

}
