package com.igrejasobrenatural.sistemas.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.igrejasobrenatural.sistemas.enums.PatrimonioEnum;
import com.igrejasobrenatural.sistemas.enums.PatrimonioEnumDeserializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "patrimonio")
public class Patrimonio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cod_patrimonio", nullable = false, unique = true)
    private Long codigo;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "data_aquisicao", nullable = false)
    private LocalDate dataAquisicao;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @Column(name = "numero_serie", nullable = false)
    private String numeroSerie;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "patrimonio_situacao", joinColumns = @JoinColumn(name = "patrimonio_id"))
    @Enumerated(EnumType.STRING)
    @JsonDeserialize(using = PatrimonioEnumDeserializer.class)
    private Set<PatrimonioEnum> situacao = new HashSet<>();

}
