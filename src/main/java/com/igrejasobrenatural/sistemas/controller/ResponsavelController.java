package com.igrejasobrenatural.sistemas.controller;

import com.igrejasobrenatural.sistemas.model.Responsavel;
import com.igrejasobrenatural.sistemas.service.ResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/responsavel")
public class ResponsavelController {

    final private ResponsavelService responsavelService;

    @Autowired
    public ResponsavelController(ResponsavelService responsavelService) {
        this.responsavelService = responsavelService;
    }

    @PostMapping
    public void salvar(@RequestBody Responsavel responsavel) {
        try {
            responsavelService.salvar(responsavel);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Responsavel>> allResponsavel() {
        return responsavelService.allResponsavel();
    }

}