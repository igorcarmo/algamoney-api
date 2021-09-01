package com.igorac.algamoney.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "lancamento")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @NotNull
    private String descricao;
    @NotNull
    @Column(name = "data_vencimento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataVencimento;
    @Column(name = "data_pagamento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataPagamento;
    @NotNull
    private BigDecimal valor;
    private String observacao;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "codigo_categoria", referencedColumnName = "codigo", nullable = false)
    private Categoria categoria;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "codigo_pessoa", referencedColumnName = "codigo", nullable = false)
    private Pessoa pessoa;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Lancamento that = (Lancamento) o;

        return Objects.equals(codigo, that.codigo) &&
            Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.codigo, this.descricao);
    }
}
