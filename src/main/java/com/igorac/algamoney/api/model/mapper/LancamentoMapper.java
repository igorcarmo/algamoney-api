package com.igorac.algamoney.api.model.mapper;

import com.igorac.algamoney.api.core.objects.LancamentoDto;
import com.igorac.algamoney.api.model.Lancamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LancamentoMapper extends ModelMapper<Lancamento, LancamentoDto> {
}
