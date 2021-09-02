package dev.igorac.algamoney.api.model.mapper;

import dev.igorac.algamoney.api.core.objects.LancamentoDto;
import dev.igorac.algamoney.api.model.Lancamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LancamentoMapper extends ModelMapper<Lancamento, LancamentoDto> {
}
