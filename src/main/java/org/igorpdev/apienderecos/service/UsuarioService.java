package org.igorpdev.apienderecos.service;

import java.util.Optional;

import org.igorpdev.apienderecos.error.ResourceNotFoundException;
import org.igorpdev.apienderecos.model.Usuario;
import org.igorpdev.apienderecos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository repository;

    public Optional<Usuario> CadastroUsuario(Usuario usuario) {
        if (repository.findByEmail(usuario.getEmail()).isPresent()) {
            if (repository.findByCpf(usuario.getCpf()).isPresent()) {
            return null;
            }
        }
    return Optional.of(repository.save(usuario));
    }

    public Usuario ListarEnderecos(long idUsuario) throws ResourceNotFoundException {
        Optional<Usuario> user = repository.findById(idUsuario);

        if(user.isPresent() == false) {
            throw new ResourceNotFoundException("Usuário de ID: " + idUsuario + " não foi encontrado.");
        }

        user.get().getEnderecos();
        repository.save(user.get());

        return repository.save(user.get());
    }

}
