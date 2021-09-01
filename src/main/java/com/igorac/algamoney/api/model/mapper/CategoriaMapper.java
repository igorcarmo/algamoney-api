package com.igorac.algamoney.api.model.mapper;

import com.igorac.algamoney.api.core.objects.CategoriaDto;
import com.igorac.algamoney.api.model.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper extends ModelMapper<Categoria, CategoriaDto> {
}
