package com.micro1.inventario.repositories;

import com.micro1.inventario.entities.Catalogo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogoRepository extends JpaRepository<Catalogo, Long>, JpaSpecificationExecutor<Catalogo> {

    // Búsqueda por nombre (con paginación)
    Page<Catalogo> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);

    // Filtro por estado
    Page<Catalogo> findByEstado(String estado, Pageable pageable);

    // Filtro por categoría
    Page<Catalogo> findByCategoria(String categoria, Pageable pageable);

    // Búsqueda por nombre exacto
    Optional<Catalogo> findByNombreIgnoreCase(String nombre);

    // Listar por estado y categoría
    Page<Catalogo> findByEstadoAndCategoria(String estado, String categoria, Pageable pageable);

    // Verificar si existe por nombre
    boolean existsByNombreIgnoreCase(String nombre);

    // Búsqueda avanzada combinada (nombre y estado)
    Page<Catalogo> findByNombreContainingIgnoreCaseAndEstado(String nombre, String estado, Pageable pageable);
}
