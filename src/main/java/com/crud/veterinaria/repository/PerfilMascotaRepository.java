package com.crud.veterinaria.repository;

import com.crud.veterinaria.model.PerfilMascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilMascotaRepository extends JpaRepository<PerfilMascota, Long> {
}