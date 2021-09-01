package com.igorac.algamoney.api.core.objects;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class EnderecoDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String logradouro;
    @NotNull
    @Size(min = 1, max = 6)
    private String numero;
    @Size(max = 50)
    private String complemento;
    @NotNull
    @Size(min = 3, max = 20)
    private String bairro;
    @NotNull
    @Size(min = 9, max = 9)
    private String cep;
    @NotNull
    @Size(min = 3, max = 20)
    private String cidade;
    @NotNull
    @Size(min = 3, max = 20)
    private String estado;
}
