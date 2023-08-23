package com.crud.veterinaria.repository;

import com.crud.veterinaria.model.InformacionGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformacionGeneralRepository extends JpaRepository<InformacionGeneral, Long> {
}