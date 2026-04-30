package com.micro2.gestion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.micro2.gestion.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
