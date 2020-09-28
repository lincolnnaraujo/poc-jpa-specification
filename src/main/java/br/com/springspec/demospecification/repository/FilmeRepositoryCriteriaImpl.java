package br.com.springspec.demospecification.repository;

import br.com.springspec.demospecification.model.Filme;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

@Repository
public class FilmeRepositoryCriteriaImpl {

    @PersistenceContext
    private final EntityManager entityManager;

    FilmeRepositoryCriteriaImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public BigDecimal somatorioValorBilheteria(Specification<Filme> specs){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> cq = cb.createQuery(BigDecimal.class);
        Root<Filme> root = cq.from(Filme.class);
        cq.select(cb.sum(root.get("valorBilheteria").as(BigDecimal.class)));

        Predicate predicate = specs.toPredicate(root, cq, cb);
        cq.where(predicate);

        return entityManager.createQuery(cq).getSingleResult();
    }
}
