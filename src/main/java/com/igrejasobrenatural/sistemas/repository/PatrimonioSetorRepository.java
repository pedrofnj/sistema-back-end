package com.igrejasobrenatural.sistemas.repository;

import com.igrejasobrenatural.sistemas.model.PatrimonioSetor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatrimonioSetorRepository extends JpaRepository<PatrimonioSetor, Long> {
    PatrimonioSetor findByNome(String nome);
}
