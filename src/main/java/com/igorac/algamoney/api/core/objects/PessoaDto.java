package com.igorac.algamoney.api.core.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PessoaDto {

    private Long codigo;
    @NotNull
    @Size(min = 3, max = 50)
    private String nome;
    @NotNull
    private Boolean ativo;
    @NotNull
    @Valid
    private EnderecoDto endereco;

    @JsonIgnore
    @Transient
    public Boolean isInativo() {
        return !this.ativo;
    }
}
