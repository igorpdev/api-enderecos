package org.igorpdev.apienderecos.repository;

import java.util.Optional;

import org.igorpdev.apienderecos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    public Optional<Usuario> findByEmail(String email);

    public Optional<Usuario> findByCpf(String cpf);

}
