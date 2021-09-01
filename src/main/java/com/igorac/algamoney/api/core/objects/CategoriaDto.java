package com.igorac.algamoney.api.core.objects;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CategoriaDto {

    private Long codigo;
    @NotNull
    @Size(min = 3, max = 20)
    private String nome;
}
