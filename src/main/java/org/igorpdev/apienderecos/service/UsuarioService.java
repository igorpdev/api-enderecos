package org.igorpdev.apienderecos.service;

import java.util.Optional;

import org.igorpdev.apienderecos.error.ResourceNotFoundException;
import org.igorpdev.apienderecos.error.UserExistsException;
import org.igorpdev.apienderecos.model.Usuario;
import org.igorpdev.apienderecos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository repository;

    public Optional<Usuario> CadastroUsuario(Usuario usuario) {
        try {
            return Optional.of(repository.save(usuario));
        } catch(Exception e) {
            throw new UserExistsException();
        }
        
    }

    public Usuario ListarEnderecos(String cpfUsuario) throws ResourceNotFoundException {
        Optional<Usuario> user = repository.findByCpf(cpfUsuario);

        if(user.isPresent() == false) {
            throw new ResourceNotFoundException();
        }

        user.get().getEnderecos();
        repository.save(user.get());

        return repository.save(user.get());
    }

}
