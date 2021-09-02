package dev.igorac.algamoney.api.repository;

import dev.igorac.algamoney.api.model.Lancamento;
import dev.igorac.algamoney.api.repository.query.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends
    JpaRepository<Lancamento, Long>,
    LancamentoRepositoryQuery {
}
