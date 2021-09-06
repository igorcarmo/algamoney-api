package dev.igorac.algamoney.api.repository.query;

import dev.igorac.algamoney.api.core.Page;
import dev.igorac.algamoney.api.core.Paging;
import dev.igorac.algamoney.api.core.filter.LancamentoFilter;
import dev.igorac.algamoney.api.model.Lancamento;
import dev.igorac.algamoney.api.repository.util.CriteriaHelper;
import dev.igorac.algamoney.api.repository.util.CriteriaHelperBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class LancamentoRepositoryQueryImpl
    implements LancamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Lancamento> filter(LancamentoFilter filter, Paging page) {
        CriteriaHelper queryHelper = CriteriaHelperBuilder.builder()
            .withRootClass(Lancamento.class)
            .withManager(entityManager)
            .build();

        Predicate[] predicates = createPredicates(queryHelper, filter);
        queryHelper.query().where(predicates);

        TypedQuery<Lancamento> query = entityManager.createQuery(queryHelper.query());
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getSize());
        return new Page<>(query.getResultList(), page, totalResults(filter));
    }

    private Long totalResults(LancamentoFilter filter) {
        CriteriaHelper countHelper = CriteriaHelperBuilder.builder()
            .withRootClass(Lancamento.class)
            .withQueryClass(Long.class)
            .withManager(entityManager)
            .build();

        Predicate[] predicates = createPredicates(countHelper, filter);
        countHelper.query().where(predicates);

        countHelper.query().select(countHelper.builder().count(countHelper.root()));
        return (Long) entityManager.createQuery(countHelper.query()).getSingleResult();
    }

    private Predicate[] createPredicates(CriteriaHelper helper, LancamentoFilter filter) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getDescricao() != null) {
            predicates.add(
                helper.builder().like(
                    helper.builder().lower(
                        helper.root().get("descricao")
                    ),"%"+filter.getDescricao().toLowerCase()+"%"
                )
            );
        }
        if (filter.getDataVencimentoDe() != null) {
            predicates.add(
                helper.builder().greaterThanOrEqualTo(
                    helper.root().get("dataVencimento"),
                    filter.getDataVencimentoDe())
            );

        }
        if (filter.getDataVencimentoAte() != null) {
            predicates.add(
                helper.builder().lessThanOrEqualTo(
                    helper.root().get("dataVencimento"),
                    filter.getDataVencimentoAte()
                )
            );

        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }

}
