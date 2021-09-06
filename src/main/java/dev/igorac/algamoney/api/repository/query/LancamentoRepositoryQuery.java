package dev.igorac.algamoney.api.repository.query;

import dev.igorac.algamoney.api.core.Paging;
import dev.igorac.algamoney.api.model.Lancamento;
import dev.igorac.algamoney.api.core.filter.LancamentoFilter;

import java.util.List;

public interface LancamentoRepositoryQuery {

    List<Lancamento> filter(LancamentoFilter filter, Paging page);
}
