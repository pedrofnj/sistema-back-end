package com.igrejasobrenatural.sistemas.service;

import com.igrejasobrenatural.sistemas.enums.PatrimonioEnum;
import com.igrejasobrenatural.sistemas.model.Patrimonio;
import com.igrejasobrenatural.sistemas.repository.PatrimonioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PatrimonioService {

    final private PatrimonioRepository patrimonioRepository;

    @Autowired
    public PatrimonioService(PatrimonioRepository patrimonioRepository) {
        this.patrimonioRepository = patrimonioRepository;
    }

    public void salvar(Patrimonio patrimonio) {
        if (patrimonioRepository.findByCodigo(patrimonio.getCodigo()).isPresent()) {
            throw new RuntimeException("Patrimônio já existe");
        }
        patrimonioRepository.save(patrimonio);
    }

    public ResponseEntity<List<Patrimonio>> byAll() {
        List<Patrimonio> patrimonios = patrimonioRepository.findAll().stream()
                .map(this::formatarSituacao)
                .collect(Collectors.toList());
        return ResponseEntity.ok(patrimonios);
    }

    private Patrimonio formatarSituacao(Patrimonio patrimonio) {
        Set<PatrimonioEnum> situacoesFormatadas = patrimonio.getSituacao().stream()
                .map(situacao -> PatrimonioEnum.valueOf(situacao.name()))
                .collect(Collectors.toSet());
        patrimonio.setSituacao(situacoesFormatadas);
        return patrimonio;
    }

    public ResponseEntity<Patrimonio> byId(Long id) {
        return ResponseEntity.ok(patrimonioRepository.findById(id).orElseThrow(() -> new RuntimeException("Patrimônio não encontrado")));
    }

    public void alterar(Long id, Patrimonio patrimonio){
        patrimonioRepository.findById(id).map(patrimonio1 -> {
            patrimonio1.setCodigo(patrimonio.getCodigo());
            patrimonio1.setNome(patrimonio.getNome());
            patrimonio1.setDescricao(patrimonio.getDescricao());
            patrimonio1.setDataAquisicao(patrimonio.getDataAquisicao());
            patrimonio1.setDataCadastro(patrimonio.getDataCadastro());
            patrimonio1.setNumeroSerie(patrimonio.getNumeroSerie());
            patrimonio1.setValor(patrimonio.getValor());
            patrimonio1.setSituacao(patrimonio.getSituacao());
            return patrimonioRepository.save(patrimonio1);
        }).orElseThrow(() -> new RuntimeException("Patrimônio não encontrado"));
    }

}
