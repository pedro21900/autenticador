package manager.excel.core.repository;

import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.core.ResolvableType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractRepositoryEx<T, ID> implements RepositoryEx<T, ID> {

    @PersistenceContext
    protected EntityManager em;

    private Class<T> entityClass;

    protected AbstractRepositoryEx() {
        this.entityClass = (Class<T>) ResolvableType.forClass(this.getClass()).getSuperType().getGeneric(0).getRawClass();
    }

    @Override
    public List<SummaryResult> summarize(Specification<T> specification, List<SummaryRequest> summariesRequest) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<T> root = cq.from(entityClass);
        Selection[] selections = summariesRequest
                .stream()
                .map(agg -> agg.getOperation().getSqlDef().apply(Triple.of(agg.getDataField(), root, cb)))
                .toArray(Selection[]::new);
        cq.multiselect(selections);
        applySpecification(specification, root, cq, cb);
        TypedQuery<Tuple> query = em.createQuery(cq);
        Tuple tuple = query.getSingleResult();
        List<SummaryResult> summariesResult = new ArrayList<>();
        for (int i = 0; i < tuple.getElements().size(); i++)
            summariesResult.add(
                    new SummaryResult(
                            summariesRequest.get(i).getDataField(),
                            tuple.get(i),
                            summariesRequest.get(i).getOperation()
                    ));
        return summariesResult;
    }

    @Override
    public List<SummaryResult> summarize(List<SummaryRequest> summariesRequest) {
        return summarize(null, summariesRequest);
    }

    @Override
    @Transactional
    @SneakyThrows
    public void patch(ID id, T entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<T> criteriaUpdate = cb.createCriteriaUpdate(this.entityClass);
        Root<T> root = criteriaUpdate.from(this.entityClass);
        applySetUpdate(criteriaUpdate, root, entity);
        criteriaUpdate.where(cb.equal(root.get("id"), id));
        em.createQuery(criteriaUpdate).executeUpdate();
    }

    @SneakyThrows
    private void applySetUpdate(CriteriaUpdate<T> criteriaUpdate, Root<T> root, T entity) {
        EntityType<T> entityType= em.getMetamodel().entity(entityClass);
        for (Attribute<? super T, ?> attribute : entityType.getAttributes()) {
            Field declaredField = entity.getClass().getDeclaredField(attribute.getName());
            declaredField.setAccessible(true);
            Object fieldValue = declaredField.get(entity);
            if (Objects.nonNull(fieldValue)) {
                criteriaUpdate.set(root.get(declaredField.getName()), fieldValue);
            }
        }

    }

    protected <X> void applySpecification(Specification<X> specification, Root<X> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        if (Objects.nonNull(specification) && Objects.nonNull(specification.toPredicate(root, cq, cb))) {
            cq.where(specification.toPredicate(root, cq, cb));
        }
    }

    protected void applyPagination(TypedQuery<?> q, Pageable pageable) {
        if (Objects.nonNull(pageable)) {
            q.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
            q.setMaxResults(pageable.getPageSize());
        }
    }

    protected <X> void applySort(Root<X> root, CriteriaQuery<?> cq, CriteriaBuilder cb, Pageable pageable) {
        if (Objects.nonNull(pageable) && Objects.nonNull(pageable.getSort())) {
            cq.orderBy(pageable.getSort()
                    .stream()
                    .map(o -> o.isAscending() ? cb.asc(root.get(o.getProperty())) : cb.desc(root.get(o.getProperty())))
                    .toArray(Order[]::new));
        }
    }

    protected <X> long count(Class<X> countEntityClass, Specification<X> specification) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Number> cq = cb.createQuery(Number.class);
        Root<X> root = cq.from(countEntityClass);
        cq.select(cb.count(root));
        applySpecification(specification, root, cq, cb);
        TypedQuery<Number> query = em.createQuery(cq);
        return query.getSingleResult().longValue();
    }

}
