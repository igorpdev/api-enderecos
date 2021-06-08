package org.igorpdev.apienderecos.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.igorpdev.apienderecos.model.Usuario;

public class UsuarioRespostaDTO {
    
    private String nome;

    private String email;

    private String cpf;

    @JsonFormat(pattern="dd-MM-yyyy")
    private Date nascimento;

    private UsuarioRespostaDTO(String nome, String email, String cpf, Date nascimento) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.nascimento = nascimento;
    }

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

    public static UsuarioRespostaDTO transformaEmDTO(Usuario usuario) {
        return new UsuarioRespostaDTO(usuario.getNome(), usuario.getEmail(), usuario.getCpf(), usuario.getNascimento());
    }
}
