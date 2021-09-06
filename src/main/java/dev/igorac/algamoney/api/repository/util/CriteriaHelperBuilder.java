package dev.igorac.algamoney.api.repository.util;

import javax.persistence.EntityManager;

public class CriteriaHelperBuilder {

    private Class rootClass;
    private Class queryClass;
    private EntityManager entityManager;

    public static CriteriaHelperBuilder builder() {
        return new CriteriaHelperBuilder();
    }

    public CriteriaHelperBuilder withRootClass(Class rootClass) {
        this.rootClass = rootClass;
        return this;
    }

    public CriteriaHelperBuilder withQueryClass(Class queryClass) {
        this.queryClass = queryClass;
        return this;
    }

    public CriteriaHelperBuilder withManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        return this;
    }

    public CriteriaHelper build() {
        if (queryClass != null) return new CriteriaHelper(this.rootClass, this.queryClass, this.entityManager);

        return new CriteriaHelper(this.rootClass, this.entityManager);
    }

}
