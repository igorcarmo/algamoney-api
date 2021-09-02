package dev.igorac.algamoney.api.repository.query;

import dev.igorac.algamoney.api.model.Lancamento;
import dev.igorac.algamoney.api.core.filter.LancamentoFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class LancamentoRepositoryQueryImpl
    implements LancamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<Lancamento> criteriaQuery;
    private Root<Lancamento> root;

    @Override
    public List<Lancamento> filter(LancamentoFilter filter) {
        build();
        Predicate[] predicates = createPredicates(filter);
        criteriaQuery.where(predicates);

        TypedQuery<Lancamento> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    private Predicate[] createPredicates(LancamentoFilter filter) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getDescricao() != null) {
            predicates.add(
                criteriaBuilder.like(
                    criteriaBuilder.lower(
                        root.get("descricao")
                    ),"%"+filter.getDescricao().toLowerCase()+"%"
                )
            );
        }
        if (filter.getDataVencimentoDe() != null) {
            predicates.add(
                criteriaBuilder.greaterThanOrEqualTo(
                    root.get("dataVencimento"),
                    filter.getDataVencimentoDe())
            );

        }
        if (filter.getDataVencimentoAte() != null) {
            predicates.add(
                criteriaBuilder.lessThanOrEqualTo(
                    root.get("dataVencimento"),
                    filter.getDataVencimentoAte()
                )
            );

        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void build(){
        criteriaBuilder = entityManager.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(Lancamento.class);
        root = criteriaQuery.from(Lancamento.class);
    }
}
