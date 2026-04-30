package com.micro2.gestion.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.micro2.gestion.entities.Usuario;
import com.micro2.gestion.repositories.UsuarioRepository;
import com.micro2.gestion.services.UsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository repository;

    @Override
    public List<Usuario> findAll() { return repository.findAll(); }

    @Override
    public Optional<Usuario> findById(Long id) { return repository.findById(id); }

    @Override
    public Usuario save(Usuario usuario) { return repository.save(usuario); }

    @Override
    public Usuario update(Long id, Usuario usuario) {
        Optional<Usuario> usuarioExistente = repository.findById(id);

        // Si el usuario no existe, usamos el id recibido y lo guardamos como nuevo.
        if (usuarioExistente.isEmpty()) {
            usuario.setId(id);
            return repository.save(usuario);
        }

        // Aquí copiamos solo los campos sencillos del usuario encontrado.
        Usuario usuarioActual = usuarioExistente.get();
        usuarioActual.setNombres(usuario.getNombres());
        usuarioActual.setCorreo(usuario.getCorreo());
        usuarioActual.setEdad(usuario.getEdad());
        usuarioActual.setEstado(usuario.getEstado());
        return repository.save(usuarioActual);
    }

    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
