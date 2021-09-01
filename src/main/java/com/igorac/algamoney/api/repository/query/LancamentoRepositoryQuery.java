package com.igorac.algamoney.api.repository.query;

import com.igorac.algamoney.api.model.Lancamento;
import com.igorac.algamoney.api.core.filter.LancamentoFilter;

import java.util.List;

public interface LancamentoRepositoryQuery {

    List<Lancamento> filter(LancamentoFilter filter);
}
