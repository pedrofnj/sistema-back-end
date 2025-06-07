package com.igrejasobrenatural.sistemas.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

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

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "data_aquisicao")
    private LocalDate dataAquisicao;

    @Column(name = "nota_fiscal")
    private String notaFical;

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDate dataCadastro;

    @Column(name = "numero_serie", nullable = false)
    private String numeroSerie;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "localizacao", nullable = false)
    private String localizacao;

    @ManyToOne
    @JoinColumn(name = "id_status", nullable = false)
    private PatrimonioStatus patrimonioStatus;

    @ManyToOne
    @JoinColumn(name = "id_patrimonio_setor", nullable = false)
    private PatrimonioSetor setores;

    @ManyToOne
    @JoinColumn(name = "id_responsavel")
    private Responsavel responsavel;

    @PrePersist
    private void prePersist() {
        this.dataCadastro = LocalDate.now();
    }

    @Transient
    private Long idSetor;

    @Transient
    private Long idStatus;

}
