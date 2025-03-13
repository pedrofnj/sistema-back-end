package com.igrejasobrenatural.sistemas.service;

import com.igrejasobrenatural.sistemas.model.Usuario;
import com.igrejasobrenatural.sistemas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {


    final private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        this.usuarioRepository = repository;
    }

    public boolean login(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null || usuario.getPassword() == null) {
            return false;
        }
        return usuario.getPassword().equals(password);
    }

    public void salvar(Usuario usuario) {
        if (usuarioRepository.findByUsername(usuario.getUsername()) != null) {
            throw new RuntimeException("Usuário já existe");
        }
        usuarioRepository.save(usuario);
    }

    public ResponseEntity<List<Usuario>> byAll() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }
}
