package org.igorpdev.apienderecos.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.igorpdev.apienderecos.model.Usuario;

public class UsuarioDTO {
    
    private String nome;

    private String email;

    private String cpf;

    @JsonFormat(pattern="dd-MM-yyyy")
    private Date nascimento;

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    public String getCpf() {
        return this.cpf;
    }

    public Date getNascimento() {
        return this.nascimento;
    }

    public Usuario transformaParaObjeto(){
        return new Usuario(nome, email, cpf, nascimento);
    }

}
