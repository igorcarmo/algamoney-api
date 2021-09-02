package dev.igorac.algamoney.api.model.mapper;

import dev.igorac.algamoney.api.core.objects.TipoLancamentoDto;
import dev.igorac.algamoney.api.model.TipoLancamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoLancamentoMapper extends ModelMapper<TipoLancamento, TipoLancamentoDto> {
}
