package com.igrejasobrenatural.sistemas.controller;

import com.igrejasobrenatural.sistemas.model.Patrimonio;
import com.igrejasobrenatural.sistemas.model.PatrimonioSetor;
import com.igrejasobrenatural.sistemas.model.PatrimonioStatus;
import com.igrejasobrenatural.sistemas.service.PatrimonioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patrimonio")
@CrossOrigin(origins = "*")
public class PatrimonioController {

    final private PatrimonioService patrimonioService;

    @Autowired
    public PatrimonioController(PatrimonioService patrimonioService) {
        this.patrimonioService = patrimonioService;
    }

    @RequestMapping("/salvar")
    public void salvar(@RequestBody Patrimonio patrimonio) {
        patrimonioService.salvar(patrimonio);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patrimonio>> findAll() {
        return patrimonioService.findAll();
    }

    @RequestMapping("/byId/{id}")
    public ResponseEntity<Patrimonio> byId(@PathVariable("id") Long id) {
        return patrimonioService.byId(id);
    }

    @GetMapping("/byCodigo/{codigo}")
    public ResponseEntity<Patrimonio> byCodigo(@PathVariable("codigo") Long codigo) {
        return patrimonioService.byCodigo(codigo);
    }

    @PostMapping("/alterar/{id}")
    public void alterar(@PathVariable("id") Long id, @RequestBody Patrimonio patrimonio) {
        patrimonioService.alterar(id, patrimonio);
    }

    @GetMapping("/setores")
    public ResponseEntity<List<PatrimonioSetor>> findAllSetores() {
        return patrimonioService.findAllSetores();
    }

    @GetMapping("/status")
    public ResponseEntity<List<PatrimonioStatus>> findAllStatus() {
        return patrimonioService.findAllStatus();
    }

}
