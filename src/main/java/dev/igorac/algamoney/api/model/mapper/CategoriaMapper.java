package dev.igorac.algamoney.api.model.mapper;

import dev.igorac.algamoney.api.core.objects.CategoriaDto;
import dev.igorac.algamoney.api.model.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper extends ModelMapper<Categoria, CategoriaDto> {
}
