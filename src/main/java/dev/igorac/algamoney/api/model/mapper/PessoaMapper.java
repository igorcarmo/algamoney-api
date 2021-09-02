package dev.igorac.algamoney.api.model.mapper;

import dev.igorac.algamoney.api.core.objects.PessoaDto;
import dev.igorac.algamoney.api.model.Pessoa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PessoaMapper extends ModelMapper<Pessoa, PessoaDto> {
}
