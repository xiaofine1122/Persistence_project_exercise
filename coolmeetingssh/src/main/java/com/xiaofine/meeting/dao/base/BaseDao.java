package com.xiaofine.meeting.dao.base;

import java.io.Serializable;
import java.util.List;

/**
 * @author: xiaofine
 */
public interface BaseDao<T> {
    T findById(Serializable key);
    void save(T entity);
    void update(T entity);
    void delete(Serializable key);
    List<T> findAll();
}
