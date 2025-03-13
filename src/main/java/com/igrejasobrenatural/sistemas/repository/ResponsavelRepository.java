package com.igrejasobrenatural.sistemas.repository;

import com.igrejasobrenatural.sistemas.model.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
    Responsavel findByNome(String nome);
}
