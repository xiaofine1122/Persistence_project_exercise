package com.xiaofine.meeting.dao.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author: xiaofine
 */
@Transactional
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    public abstract Class getEntityClass();

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(T entity) {
        getSession().persist(entity);
    }

    public void update(T entity) {
        getSession().update(entity);
    }

    public T findById(Serializable key) {
        return (T) getSession().get(getEntityClass(), key);
    }

    public void delete(Serializable key) {
        getSession().delete(findById(key));
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> findAll() {
        String hql = "from "+getEntityClass().getName();
        Query query = getSession().createQuery(hql);
        return query.list();
    }

}
