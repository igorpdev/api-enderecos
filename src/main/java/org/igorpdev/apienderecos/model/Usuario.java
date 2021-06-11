package org.igorpdev.apienderecos.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.validator.constraints.br.CPF;
import org.igorpdev.apienderecos.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;
    
    @NotNull
    private String nome;

    @Email
    @Column(unique = true)
    @NotNull
    private String email;

    @CPF
    @Column(unique = true)
    @NotNull
    private String cpf;

    @NotNull
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date nascimento;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("usuario")
    private List<Endereco> enderecos;

    public UsuarioDTO toDto(ResponseEntity<Usuario> usuario) {
        UsuarioDTO uDto = new UsuarioDTO();
        uDto.setNome(this.nome);
        uDto.setEmail(this.email);
        uDto.setCpf(this.cpf);
        uDto.setNascimento(this.nascimento);
        uDto.setEnderecos(this.enderecos);
        return uDto;
    }
 
    public long getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
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

    public List<Endereco> getEnderecos() {
        return this.enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

}
