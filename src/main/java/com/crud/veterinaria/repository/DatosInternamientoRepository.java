package com.crud.veterinaria.repository;

import com.crud.veterinaria.model.DatosInternamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatosInternamientoRepository extends JpaRepository<DatosInternamiento, Long> {
}