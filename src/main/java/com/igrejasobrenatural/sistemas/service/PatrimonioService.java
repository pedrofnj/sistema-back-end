package com.igrejasobrenatural.sistemas.service;

import com.igrejasobrenatural.sistemas.model.Patrimonio;
import com.igrejasobrenatural.sistemas.model.PatrimonioStatus;
import com.igrejasobrenatural.sistemas.repository.PatrimonioRepository;
import com.igrejasobrenatural.sistemas.repository.PatrimonioStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PatrimonioService {

    final private PatrimonioRepository patrimonioRepository;

    final private PatrimonioStatusRepository patrimonioStatusRepository;

    @Autowired
    public PatrimonioService(PatrimonioRepository patrimonioRepository, PatrimonioStatusRepository patrimonioStatusRepository) {
        this.patrimonioRepository = patrimonioRepository;
        this.patrimonioStatusRepository = patrimonioStatusRepository;
    }

    public void salvar(Patrimonio patrimonio) {
        if (patrimonioRepository.findByCodigo(patrimonio.getCodigo()).isPresent()) {
            throw new RuntimeException("Patrimônio já existe");
        }

        Long idStatus = patrimonio.getPatrimonioStatus().getId();
        PatrimonioStatus status = patrimonioStatusRepository.findById(idStatus)
                .orElseThrow(() -> new RuntimeException("Status de patrimônio não encontrado"));

        patrimonio.setPatrimonioStatus(status);

        patrimonioRepository.save(patrimonio);
    }

    public ResponseEntity<Patrimonio> byId(Long id) {
        return ResponseEntity.ok(patrimonioRepository.findById(id).orElseThrow(() -> new RuntimeException("Patrimônio não encontrado")));
    }

    public void alterar(Long id, Patrimonio patrimonio){
        patrimonioRepository.findById(id).map(atualizaPatrimonio -> {
            atualizaPatrimonio.setCodigo(patrimonio.getCodigo());
            atualizaPatrimonio.setNome(patrimonio.getNome());
            atualizaPatrimonio.setDescricao(patrimonio.getDescricao());
            atualizaPatrimonio.setDataAquisicao(patrimonio.getDataAquisicao());
            atualizaPatrimonio.setDataCadastro(patrimonio.getDataCadastro());
            atualizaPatrimonio.setNumeroSerie(patrimonio.getNumeroSerie());
            atualizaPatrimonio.setValor(patrimonio.getValor());
            atualizaPatrimonio.setPatrimonioStatus(patrimonio.getPatrimonioStatus());
            return patrimonioRepository.save(atualizaPatrimonio);
        }).orElseThrow(() -> new RuntimeException("Patrimônio não encontrado"));
    }

}
