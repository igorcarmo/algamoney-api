package com.igorac.algamoney.api.model.mapper;


import com.igorac.algamoney.api.core.objects.EnderecoDto;
import com.igorac.algamoney.api.model.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper extends ModelMapper<Endereco, EnderecoDto> {
}
