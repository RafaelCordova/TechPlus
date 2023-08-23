package com.crud.veterinaria.repository;


import com.crud.veterinaria.model.DatosPropietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatosPropietarioRepository extends JpaRepository<DatosPropietario, Long> {
}