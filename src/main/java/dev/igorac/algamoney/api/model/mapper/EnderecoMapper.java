package dev.igorac.algamoney.api.model.mapper;


import dev.igorac.algamoney.api.core.objects.EnderecoDto;
import dev.igorac.algamoney.api.model.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper extends ModelMapper<Endereco, EnderecoDto> {
}
