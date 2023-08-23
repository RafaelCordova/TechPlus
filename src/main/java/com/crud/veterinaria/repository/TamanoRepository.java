package com.crud.veterinaria.repository;

import com.crud.veterinaria.model.Tamano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TamanoRepository extends JpaRepository<Tamano, Long> {
    // Aquí puedes agregar métodos de consulta específicos si los necesitas
}