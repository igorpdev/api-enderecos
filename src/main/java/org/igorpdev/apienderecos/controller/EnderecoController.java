package org.igorpdev.apienderecos.controller;

import java.util.List;

import org.igorpdev.apienderecos.model.Endereco;
import org.igorpdev.apienderecos.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/enderecos")
public class EnderecoController {
    
    @Autowired
    private EnderecoRepository repository;

    @GetMapping
    public ResponseEntity<List<Endereco>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

}
