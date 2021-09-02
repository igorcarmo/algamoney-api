package dev.igorac.algamoney.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pessoa")
@Getter
@Setter
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @NotNull
    @Size(min = 3, max = 50)
    private String nome;
    @NotNull
    private Boolean ativo;
    @Embedded
    @NotNull
    @Valid
    private Endereco endereco;

    @Transient
    public Boolean isInativo() {
        return !this.ativo;
    }
}
