package com.igrejasobrenatural.sistemas.service;

import com.igrejasobrenatural.sistemas.model.Responsavel;
import com.igrejasobrenatural.sistemas.repository.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsavelService {

    final private ResponsavelRepository responsavelRepository;

    @Autowired
    public ResponsavelService(ResponsavelRepository repository) {
        this.responsavelRepository = repository;
    }

    public void salvar(Responsavel responsavel) {
        responsavelRepository.save(responsavel);
    }

    public ResponseEntity<List<Responsavel>> allResponsavel() {
        return ResponseEntity.ok(responsavelRepository.findAll());
    }

}
