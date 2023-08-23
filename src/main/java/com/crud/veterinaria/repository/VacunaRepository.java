package com.crud.veterinaria.repository;

import com.crud.veterinaria.model.Vacuna;
import org.springframework.data.repository.CrudRepository;

public interface VacunaRepository extends CrudRepository<Vacuna, Long> {
    // No es necesario definir métodos aquí, heredamos los métodos de CrudRepository
}

