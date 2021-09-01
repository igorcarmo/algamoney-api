package com.igorac.algamoney.api.repository;

import com.igorac.algamoney.api.model.Lancamento;
import com.igorac.algamoney.api.repository.query.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends
    JpaRepository<Lancamento, Long>,
    LancamentoRepositoryQuery {
}
