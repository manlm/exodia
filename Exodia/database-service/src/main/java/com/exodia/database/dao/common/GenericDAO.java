package com.exodia.database.dao.common;

/**
 * Created by manlm1 on 9/10/2015.
 */
public interface GenericDAO<E> {

    E save(E instance);

    E update(E instance);

    E saveOrUpdate(E instance);

    boolean remove(E entity);

}
