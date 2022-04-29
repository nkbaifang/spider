package io.ziyi.spider.showmax.dao;

import io.ziyi.spider.showmax.common.BaseComponent;
import io.ziyi.spider.showmax.common.Page;
import io.ziyi.spider.showmax.model.BaseModel;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public abstract class BaseDao extends BaseComponent {

    public static class UpdateTimeTuple {
        private final Date time;
        private final int count;

        private UpdateTimeTuple(Date time, int count) {
            this.time = time;
            this.count = count;
        }

        public Date getTime() {
            return time;
        }

        public int getCount() {
            return count;
        }

        @Override
        public boolean equals(Object other) {
            if ( this == other ) {
                return true;
            }
            if ( other == null ) {
                return false;
            }
            if ( other.getClass() != this.getClass() ) {
                return false;
            }
            UpdateTimeTuple that = (UpdateTimeTuple) other;
            return this.count == that.count && Objects.equals(this.time, that.time);
        }

        @Override
        public int hashCode() {
            int hash = this.getClass().hashCode();
            if ( time != null ) {
                hash = hash * 13 + time.hashCode();
            }
            hash = hash * 17 + count;
            return hash;
        }
    }

    public static class SorterBuilder {
        private final Map<String, Boolean> data;

        private SorterBuilder() {
            data = new LinkedHashMap<>();
        }

        public SorterBuilder asc(String... key) {
            Objects.requireNonNull(key);
            for ( String k : key ) {
                data.put(k, true);
            }
            return this;
        }

        public SorterBuilder desc(String... key) {
            Objects.requireNonNull(key);
            for ( String k : key ) {
                data.put(k, false);
            }
            return this;
        }

        public Map<String, Boolean> build() {
            return new LinkedHashMap<>(data);
        }
    }

    protected BaseDao() {
    }

    protected abstract EntityManager getEntityManager();

    protected String getTableName(Class<?> type) {
        Table table = type.getAnnotation(Table.class);
        return table == null ? null : table.name();
    }

    protected BaseModel<?> save(BaseModel<?> entity) {
        if ( entity != null ) {
            EntityManager entityManager = getEntityManager();
            entityManager.merge(entity);
        }
        return entity;
    }

    protected void saveAll(final Collection<?> entities) {
        if (entities != null && !entities.isEmpty()) {
            EntityManager entityManager = getEntityManager();
            for (Object entity : entities) {
                entityManager.merge(entity);
            }
        }
    }

    protected void delete(BaseModel<?> entity) {
        if ( entity != null ) {
            EntityManager entityManager = getEntityManager();
            entityManager.remove(entity);
        }
    }

    protected void deleteAll(Collection<? extends BaseModel<?>> entities) {
        if ( entities != null ) {
            EntityManager entityManager = getEntityManager();
            for ( BaseModel<?> entity : entities ) {
                entityManager.remove(entity);
            }
        }
    }

    protected <E extends BaseModel<? extends Serializable>> E find(Class<E> type, Serializable id) {
        if ( id == null ) {
            return null;
        }
        EntityManager entityManager = getEntityManager();
        return entityManager.find(type, id);
    }

    protected <E extends BaseModel<? extends Serializable>> E find(Class<E> type, Serializable id, boolean forUpdate) {
        if ( id == null ) {
            return null;
        }
        EntityManager entityManager = getEntityManager();
        LockModeType lockModeType = forUpdate ? LockModeType.PESSIMISTIC_WRITE : LockModeType.PESSIMISTIC_READ;
        return entityManager.find(type, id, lockModeType);
    }

    protected <E extends BaseModel<? extends Serializable>> int count(Class<E> type, Map<String, Object> filterMap) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        return queryCount(entityManager, builder, type, filterMap);
    }

    protected int count(String name, Map<String, Object> params) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Long> query = createQuery(entityManager, name, Long.class, params, -1, 0);
        return query.getSingleResult().intValue();
    }

    protected <E extends BaseModel<? extends Serializable>> List<E> query(Class<E> type, Map<String, Object> filterMap, Map<String, Boolean> orderMap, int startIndex, int maxResult, boolean forUpdate) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        return queryList(entityManager, builder, type, filterMap, orderMap, startIndex, maxResult, forUpdate);
    }

    protected <E extends BaseModel<? extends Serializable>> List<E> query(Class<E> type, Map<String, Object> filterMap, Map<String, Boolean> orderMap) {
        return query(type, filterMap, orderMap, -1, 0, false);
    }

    protected <E extends BaseModel<? extends Serializable>> List<E> query(Class<E> type, Map<String, Object> filterMap, Map<String, Boolean> orderMap, boolean forUpdate) {
        return query(type, filterMap, orderMap, -1, 0, forUpdate);
    }

    protected <E extends BaseModel<? extends Serializable>> List<E> query(Class<E> type, String name, Map<String, Object> params) {
        return query(type, name, params, -1, 0);
    }

    protected <E extends BaseModel<? extends Serializable>> List<E> query(Class<E> type, String name, Map<String, Object> params, int startIndex, int maxResult) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<E> query = createQuery(entityManager, name, type, params, startIndex, maxResult);
        List<E> result =  query.getResultList();
        return result == null ? Collections.emptyList() : result;
    }

    /**
     * Search for a unique result from database, or <code>null</code> if no such object is found.
     *
     * @param type      Class of result type
     * @param filterMap Query conditions, or <code>null</code> if query without any conditions
     * @param forUpdate Whether or not to locked the result for future update.
     * @param <E>       Type of result
     * @return The result object, or <code>null</code>
     * @throws NonUniqueResultException if more than one result is found
     */
    protected <E extends BaseModel<? extends Serializable>> E unique(Class<E> type, Map<String, Object> filterMap, boolean forUpdate) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        LockModeType lockModeType = forUpdate ? LockModeType.PESSIMISTIC_WRITE : LockModeType.PESSIMISTIC_READ;
        TypedQuery<E> query = createQuery(entityManager, builder, type, type, Function.identity(), filterMap, null, lockModeType);
        try {
            return query.getSingleResult();
        } catch ( NoResultException ex ) {
            return null;
        }
    }

    protected <E extends BaseModel<? extends Serializable>> E unique(Class<E> type, String name, Map<String, Object> params, boolean forUpdate) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<E> query = createQuery(entityManager, name, type,params, -1, 0);
        try {
            return query.getSingleResult();
        } catch ( NoResultException ex ) {
            return null;
        }
    }

    protected <E extends BaseModel<? extends Serializable>> Page<E> page(Class<E> type, Map<String, Object> filterMap, Map<String, Boolean> orderMap, int startIndex, int maxResult) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        List<E> list = queryList(entityManager, builder, type, filterMap, orderMap, startIndex, maxResult, false);
        int count = maxResult > 0 ? queryCount(entityManager, builder, type, filterMap) : list.size();
        return new Page<>(list, count);
    }

    protected <E extends BaseModel<? extends Serializable>> int delete(Class<E> type, Map<String, Object> filterMap) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<E> delete = builder.createCriteriaDelete(type);
        Root<E> root = delete.from(type);
        delete.where(predicates(builder, root, filterMap));
        return entityManager.createQuery(delete).executeUpdate();
    }

    protected SorterBuilder sorterBuilder() {
        return new SorterBuilder();
    }

    protected Map<String, Boolean> asc(String... key) {
        return new SorterBuilder().asc(key).build();
    }

    protected Map<String, Boolean> desc(String... key) {
        return new SorterBuilder().desc(key).build();
    }

    protected int executeSql(String sql, Map<String, Object> params) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createNativeQuery(sql);
        if ( params != null ) {
            params.forEach(query::setParameter);
        }
        return query.executeUpdate();
    }

    protected <E extends BaseModel<?>> UpdateTimeTuple queryRecentUpdateTime(Class<E> type) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> tupleQuery = builder.createTupleQuery();
        Root<E> entity = tupleQuery.from(type);
        tupleQuery.multiselect(builder.count(entity), builder.max(entity.get("updateTime")));
        List<Tuple> list = entityManager.createQuery(tupleQuery).getResultList();

        UpdateTimeTuple result;
        if ( list.size() > 0 ) {
            Tuple tuple = list.get(0);
            Number count = (Number) tuple.get(0);
            Date time = (Date) tuple.get(1);
            result = new UpdateTimeTuple(time, count.intValue());
        } else {
            result = new UpdateTimeTuple(new Date(), 0);
        }
        return result;
    }

    private <E, R> TypedQuery<R> createQuery(
            EntityManager entityManager, CriteriaBuilder builder,
            Class<E> entityType, Class<R> resultType, Function<Expression<E>, Expression<R>> converter,
            Map<String, Object> filterMap, Map<String, Boolean> orderMap,
            LockModeType lockModeType
    ) {
        CriteriaQuery<R> listQuery = builder.createQuery(resultType);
        Root<E> root = listQuery.from(entityType);
        listQuery.select(converter.apply(root));

        if ( filterMap != null ) {
            listQuery.where(predicates(builder, root, filterMap));
        }
        if ( orderMap != null ) {
            listQuery.orderBy(orders(builder, root, orderMap));
        }
        TypedQuery<R> query = entityManager.createQuery(listQuery);
        if ( lockModeType != null ) {
            query.setLockMode(lockModeType);
        }
        return query;
    }

    private <R> TypedQuery<R> createQuery(EntityManager entityManager, String name, Class<R> resultType, Map<String, Object> params, int startIndex, int maxResult) {
        TypedQuery<R> query = entityManager.createNamedQuery(name, resultType);
        if ( params != null ) {
            params.forEach(query::setParameter);
        }
        if ( startIndex > -1 ) {
            query.setFirstResult(startIndex);
        }
        if ( maxResult > 0 ) {
            query.setMaxResults(maxResult);
        }
        return query;
    }

    private <E> List<E> queryList(EntityManager entityManager, CriteriaBuilder builder, Class<E> type, Map<String, Object> filterMap, Map<String, Boolean> orderMap, int startIndex, int maxResult, boolean forUpdate) {
        LockModeType lockModeType = forUpdate ? LockModeType.PESSIMISTIC_WRITE : LockModeType.PESSIMISTIC_READ;
        TypedQuery<E> query = createQuery(entityManager, builder, type, type, Function.identity(), filterMap, orderMap, lockModeType);
        if ( startIndex > -1 ) {
            query.setFirstResult(startIndex);
        }
        if ( maxResult > 0 ) {
            query.setMaxResults(maxResult);
        }

        List<E> result =  query.getResultList();
        return result == null ? Collections.emptyList() : result;
    }

    private <E> int queryCount(EntityManager entityManager, CriteriaBuilder builder, Class<E> type, Map<String, Object> filterMap) {
        TypedQuery<Long> query = createQuery(entityManager, builder, type, Long.class, builder::count, filterMap, null, null);
        Number value;
        try {
            value = query.getSingleResult();
        } catch ( NoResultException ex ) {
            value = 0L;
        }

        return value.intValue();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private <E> Predicate[] predicates(CriteriaBuilder builder, Root<E> root, Map<String, Object> filter) {
        if ( filter == null ) {
            return new Predicate[0];
        }

        List<Predicate> where = new ArrayList<>(filter.size());
        for ( Map.Entry<String, Object> entry : filter.entrySet() ) {
            String key = entry.getKey();
            Object value = entry.getValue();

            String op = "eq";
            int index = key.lastIndexOf('$');
            if ( index > 0 ) {
                op = key.substring(index + 1);
                key = key.substring(0, index);
            }

            Path exp = root.get(key);
            if ( key.contains(".") ) {
                String[] names = key.split("\\.");
                exp = root.get(names[0]);
                for ( int i = 1; i < names.length; i++ ) {
                    exp = exp.get(names[i]);
                }
            }

            Predicate predicate;
            switch ( op ) {
                case "eq":
                    predicate = value == null ? builder.isNull(exp) : builder.equal(exp, value);
                    break;
                case "ne":
                    predicate = value == null ? builder.isNotNull(exp) : builder.notEqual(exp, value);
                    break;
                case "gt":
                    predicate = builder.greaterThan(exp.as(value.getClass()), (Comparable) value);
                    break;
                case "lt":
                    predicate = builder.lessThan(exp.as(value.getClass()), (Comparable) value);
                    break;
                case "ge":
                    predicate = builder.greaterThanOrEqualTo(exp.as(value.getClass()), (Comparable) value);
                    break;
                case "le":
                    predicate = builder.lessThanOrEqualTo(exp.as(value.getClass()), (Comparable) value);
                    break;
                case "like":
                    predicate = builder.like(exp.as(String.class), "%" + value + "%");
                    break;
                case "in": {
                    CriteriaBuilder.In<Object> in = builder.in(exp);
                    iterable(value).forEach(in::value);
                    predicate = in;
                    break;
                }
                case "notin": {
                    CriteriaBuilder.In<Object> in = builder.in(exp);
                    iterable(value).forEach(in::value);
                    predicate = builder.not(in);
                    break;
                }
                default:
                    throw new RuntimeException("Unsupported operator: " + op);
            }
            where.add(predicate);
        }
        return where.toArray(new Predicate[0]);
    }

    @SuppressWarnings("unchecked")
    private Iterable<Object> iterable(Object value) {
        if ( value.getClass().isArray() ) {
            int length = Array.getLength(value);
            List<Object> list = new ArrayList<>(length);
            for ( int i = 0; i < length; i++ ) {
                list.add(Array.get(value, i));
            }
            return list;
        }

        if ( value instanceof Collection ) {
            Collection<Object> values = (Collection<Object>) value;
            return new LinkedList<>(values);
        }

        return Collections.singletonList(value);
    }

    private <E> Order[] orders(CriteriaBuilder builder, Root<E> root, Map<String, Boolean> orderMap) {
        List<Order> order = new LinkedList<>();
        if ( orderMap != null && orderMap.size() > 0 ) {
            orderMap.forEach((key, value) -> {
                Expression<Object> expr = root.get(key);
                order.add(value ? builder.asc(expr) : builder.desc(expr));
            });
        }
        return order.toArray(new Order[0]);
    }
}