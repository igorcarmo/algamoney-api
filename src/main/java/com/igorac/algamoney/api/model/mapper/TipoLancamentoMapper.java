package com.igorac.algamoney.api.model.mapper;

import com.igorac.algamoney.api.core.objects.TipoLancamentoDto;
import com.igorac.algamoney.api.model.TipoLancamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoLancamentoMapper extends ModelMapper<TipoLancamento, TipoLancamentoDto> {
}
