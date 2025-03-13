package com.igrejasobrenatural.sistemas.controller;

import com.igrejasobrenatural.sistemas.model.Usuario;
import com.igrejasobrenatural.sistemas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    final private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public void salvar(@RequestBody Usuario usuario) {
        try {
            usuarioService.salvar(usuario);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> byAll() {
        return usuarioService.byAll();
    }

    @GetMapping("/test")
    public String testConect() {
        return "Test Controller";
    }
}
