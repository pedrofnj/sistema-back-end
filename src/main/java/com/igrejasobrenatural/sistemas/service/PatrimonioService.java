package com.igrejasobrenatural.sistemas.service;

import com.igrejasobrenatural.sistemas.model.Patrimonio;
import com.igrejasobrenatural.sistemas.model.PatrimonioSetor;
import com.igrejasobrenatural.sistemas.model.PatrimonioStatus;
import com.igrejasobrenatural.sistemas.repository.PatrimonioRepository;
import com.igrejasobrenatural.sistemas.repository.PatrimonioSetorRepository;
import com.igrejasobrenatural.sistemas.repository.PatrimonioStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatrimonioService {

    final private PatrimonioRepository patrimonioRepository;

    final private PatrimonioStatusRepository patrimonioStatusRepository;

    final private PatrimonioSetorRepository patrimonioSetorRepository;

    @Autowired
    public PatrimonioService(PatrimonioRepository patrimonioRepository, PatrimonioStatusRepository patrimonioStatusRepository, PatrimonioSetorRepository patrimonioSetorRepository) {
        this.patrimonioRepository = patrimonioRepository;
        this.patrimonioStatusRepository = patrimonioStatusRepository;
        this.patrimonioSetorRepository = patrimonioSetorRepository;
    }

    public Patrimonio salvar(Patrimonio patrimonio) {
        // Cadastro novo
        if (patrimonio.getId() == null) {
            if (patrimonioRepository.findByCodigo(patrimonio.getCodigo()).isPresent()) {
                throw new RuntimeException("Já existe um patrimônio com este código.");
            }
        } else {
            // Edição: impede duplicidade de código em outro registro
            patrimonioRepository.findByCodigo(patrimonio.getCodigo()).ifPresent(existing -> {
                if (!existing.getId().equals(patrimonio.getId())) {
                    throw new RuntimeException("Já existe outro patrimônio com este código.");
                }
            });
        }

        Long idStatus = patrimonio.getIdStatus();
        PatrimonioStatus status = patrimonioStatusRepository.findById(idStatus)
                .orElseThrow(() -> new RuntimeException("Status de patrimônio não encontrado"));

        Long idSetor = patrimonio.getIdSetor();
        PatrimonioSetor setor = patrimonioSetorRepository.findById(idSetor)
                .orElseThrow(() -> new RuntimeException("Setor não encontrado"));

        patrimonio.setSetores(setor);
        patrimonio.setPatrimonioStatus(status);

        return patrimonioRepository.save(patrimonio);
    }

    public ResponseEntity<List<Patrimonio>> findAll() {
        return ResponseEntity.ok(patrimonioRepository.findAll());
    }

    public ResponseEntity<List<PatrimonioSetor>> findAllSetores() {
        return ResponseEntity.ok(patrimonioSetorRepository.findAll());
    }

    public ResponseEntity<List<PatrimonioStatus>> findAllStatus() {
        return ResponseEntity.ok(patrimonioStatusRepository.findAll());
    }

    public ResponseEntity<Patrimonio> byId(Long id) {
        return ResponseEntity.ok(patrimonioRepository.findById(id).orElseThrow(() -> new RuntimeException("Patrimônio não encontrado")));
    }

    public ResponseEntity<Patrimonio> byCodigo(Long codigo) {
        return ResponseEntity.ok(patrimonioRepository.findByCodigo(codigo).orElseThrow(() -> new RuntimeException("Patrimônio não encontrado")));
    }

    public void alterar(Long id, Patrimonio patrimonio) {
        patrimonioRepository.findById(id).map(atualizaPatrimonio -> {
            atualizaPatrimonio.setCodigo(patrimonio.getCodigo());
            atualizaPatrimonio.setNome(patrimonio.getNome());
            atualizaPatrimonio.setDescricao(patrimonio.getDescricao());
            atualizaPatrimonio.setDataAquisicao(patrimonio.getDataAquisicao());
            atualizaPatrimonio.setDataCadastro(patrimonio.getDataCadastro());
            atualizaPatrimonio.setNumeroSerie(patrimonio.getNumeroSerie());
            atualizaPatrimonio.setValor(patrimonio.getValor());
            atualizaPatrimonio.setPatrimonioStatus(patrimonio.getPatrimonioStatus());
            atualizaPatrimonio.setSetores(patrimonio.getSetores());
            atualizaPatrimonio.setResponsavel(patrimonio.getResponsavel());
            return patrimonioRepository.save(atualizaPatrimonio);
        }).orElseThrow(() -> new RuntimeException("Patrimônio não encontrado"));
    }

}
