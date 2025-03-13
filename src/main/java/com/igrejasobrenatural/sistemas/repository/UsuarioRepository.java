package com.igrejasobrenatural.sistemas.repository;

import com.igrejasobrenatural.sistemas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}
