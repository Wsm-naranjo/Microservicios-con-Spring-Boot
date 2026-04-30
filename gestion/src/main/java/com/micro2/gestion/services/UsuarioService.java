package com.micro2.gestion.services;

import java.util.List;
import java.util.Optional;
import com.micro2.gestion.entities.Usuario;

public interface UsuarioService {
    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    Usuario save(Usuario usuario);
    Usuario update(Long id, Usuario usuario);
    void delete(Long id);
}
