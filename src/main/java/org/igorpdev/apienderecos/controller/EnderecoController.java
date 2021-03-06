package org.igorpdev.apienderecos.controller;


import javax.validation.Valid;

import org.igorpdev.apienderecos.dto.EnderecoDTO;
import org.igorpdev.apienderecos.model.Endereco;
import org.igorpdev.apienderecos.model.Usuario;
import org.igorpdev.apienderecos.repository.EnderecoRepository;
import org.igorpdev.apienderecos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/enderecos")
public class EnderecoController {
    
    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastro")
	public EnderecoDTO post (@Valid @RequestBody Endereco endereco) {
        return endereco.toDto(ResponseEntity.status(HttpStatus.CREATED).body(repository.save(endereco)));
	}

    @GetMapping("/usuarios/{cpf}")
    public ResponseEntity <Usuario> getAllEnderecosListados(@PathVariable String cpf) {
            return ResponseEntity.ok(usuarioService.ListarEnderecos(cpf));

    }

}
