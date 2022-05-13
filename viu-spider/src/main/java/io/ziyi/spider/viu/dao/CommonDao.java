package io.ziyi.spider.viu.dao;

import io.ziyi.spider.viu.model.BaseModel;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public class CommonDao extends BaseDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected EntityManager getEntityManager() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAll(final Collection<?> entities) {
        super.saveAll(entities);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteAll(final Collection<? extends BaseModel<?>> entities) {
        super.deleteAll(entities);
    }

    @Transactional(readOnly = true)
    public <K extends Serializable, E extends BaseModel<K>> E find(Class<E> type, K key) {
        return super.find(type, key);
    }

    @Transactional(rollbackFor = Exception.class)
    public <K extends Serializable, E extends BaseModel<K>> E find(Class<E> type, K key, boolean forUpdate) {
        return super.find(type, key, forUpdate);
    }

    @Transactional(readOnly = true)
    public int count(String name, Map<String, Object> params) {
        return super.count(name, params);
    }
}
