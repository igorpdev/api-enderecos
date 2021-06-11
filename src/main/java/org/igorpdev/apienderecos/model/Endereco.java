package org.igorpdev.apienderecos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.igorpdev.apienderecos.dto.EnderecoDTO;
import org.springframework.http.ResponseEntity;

@Entity
@Table(name = "tb_endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idEndereco;
	
	@Column(nullable = false)
	private String logradouro;
	
	@Column(nullable = false)
	private int numero;
	
	@Column(nullable = false)
	private String complemento;
	
	@Column(nullable = false)
	private String bairro;
	
	@Column(nullable = false)
	private String cidade;
	
	@Column(nullable = false)
	private String estado;
	
	@Column(nullable = false)
	@JsonFormat(pattern = "00000-000")
	private String cep;
	
	@ManyToOne
	@JsonIgnoreProperties("usuario")
	private Usuario usuario;

	public EnderecoDTO toDto(ResponseEntity<Endereco> enderec0) {
        EnderecoDTO eDto = new EnderecoDTO();
        eDto.setLogradouro(this.logradouro);
        eDto.setNumero(this.numero);
		eDto.setComplemento(this.complemento);
		eDto.setBairro(this.bairro);
		eDto.setCidade(this.cidade);
		eDto.setEstado(this.estado);
		eDto.setCep(this.cep);
		eDto.setUsuario(this.usuario);
        return eDto;
    }

	public long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(long id) {
		this.idEndereco = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
