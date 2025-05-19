package com.igrejasobrenatural.sistemas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patrimonio_setor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatrimonioSetor {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome")
    private String nome;
}
