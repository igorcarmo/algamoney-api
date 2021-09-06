package dev.igorac.algamoney.api.repository.util;

import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CriteriaHelper {

    private final CriteriaBuilder criteriaBuilder;
    private final CriteriaQuery criteriaQuery;
    private final Root root;

    protected CriteriaHelper(Class rootClass, EntityManager entityManager) {
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
        this.criteriaQuery = this.criteriaBuilder.createQuery(rootClass);
        this.root = this.criteriaQuery.from(rootClass);
    }

    protected CriteriaHelper(Class rootClass, Class queryClass, EntityManager entityManager) {
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
        this.criteriaQuery = this.criteriaBuilder.createQuery(queryClass);
        this.root = this.criteriaQuery.from(rootClass);
    }

    public CriteriaQuery query() {
        return this.criteriaQuery;
    }

    public Root root() {
        return this.root;
    }

    public CriteriaBuilder builder() {
        return this.criteriaBuilder;
    }

}




