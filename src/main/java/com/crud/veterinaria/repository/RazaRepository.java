package com.crud.veterinaria.repository;

import com.crud.veterinaria.model.Raza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RazaRepository extends JpaRepository<Raza, Long> {
}