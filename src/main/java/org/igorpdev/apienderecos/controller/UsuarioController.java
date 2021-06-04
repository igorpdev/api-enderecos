package org.igorpdev.apienderecos.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.igorpdev.apienderecos.model.Usuario;
import org.igorpdev.apienderecos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> post(@Valid @RequestBody Usuario usuario) {
        Optional<Usuario> user = service.CadastroUsuario(usuario);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(user.get());

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
