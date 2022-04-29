package io.ziyi.spider.showmax.dao;

import io.ziyi.spider.showmax.model.BaseModel;
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
    public <E extends BaseModel<? extends Serializable>> E find(Class<E> type, Serializable id) {
        return super.find(type, id);
    }

    @Transactional(rollbackFor = Exception.class)
    public <E extends BaseModel<? extends Serializable>> E find(Class<E> type, Serializable id, boolean forUpdate) {
        return super.find(type, id, forUpdate);
    }

    @Transactional(readOnly = true)
    public int count(String name, Map<String, Object> params) {
        return super.count(name, params);
    }
}
