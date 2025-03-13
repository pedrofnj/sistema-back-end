package com.igrejasobrenatural.sistemas.repository;

import com.igrejasobrenatural.sistemas.model.Patrimonio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatrimonioRepository extends JpaRepository<Patrimonio, Long> {
    Optional<Patrimonio> findByCodigo(Long codigo);
}
