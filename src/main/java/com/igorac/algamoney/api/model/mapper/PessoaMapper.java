package com.igorac.algamoney.api.model.mapper;

import com.igorac.algamoney.api.core.objects.PessoaDto;
import com.igorac.algamoney.api.model.Pessoa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PessoaMapper extends ModelMapper<Pessoa, PessoaDto> {
}
