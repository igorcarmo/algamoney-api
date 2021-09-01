package com.igorac.algamoney.api.core.objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LancamentoDto {

    private Long codigo;
    @NotNull
    private String descricao;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataVencimento;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataPagamento;
    @NotNull
    private BigDecimal valor;
    private String observacao;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoLancamentoDto tipo;
    @NotNull
    private CategoriaDto categoria;
    @NotNull
    private PessoaDto pessoa;

}
