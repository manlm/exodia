package com.exodia.database.dao.common;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * Created by manlm1 on 9/10/2015.
 */
public abstract class GenericHibernateDAO<E> implements GenericDAO<E>, Serializable {

    private static final Logger LOG = Logger.getLogger(GenericHibernateDAO.class);

    @Autowired
    SessionFactory sessionFactory;

    /**
     * Insert a record into table
     *
     * @param entity
     * @return
     */
    @Override
    public E save(E entity) {
        LOG.info(new StringBuilder("[save] Start: entity = ").append(entity));

        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            LOG.error(new StringBuilder("[save] Error: ").append(e.getMessage()));
            session.getTransaction().rollback();
            return null;
        }
        LOG.info("[save] End");
        return entity;
    }

    /**
     * Update a record in table
     *
     * @param entity
     * @return
     */
    @Override
    public E update(E entity) {
        LOG.info(new StringBuilder("[update] Start: entity = ").append(entity));

        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            LOG.error(new StringBuilder("[update] Error: ").append(e.getMessage()));
            session.getTransaction().rollback();
            return null;
        }
        LOG.info("[update] End");
        return entity;
    }

    @Override
    public E saveOrUpdate(E entity) {
        LOG.info(new StringBuilder("[save] Start: entity = ").append(entity));

        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            LOG.error(new StringBuilder("[save] Error: ").append(e.getMessage()));
            session.getTransaction().rollback();
            return null;
        }
        LOG.info("[save] End");
        return entity;
    }

    /**
     * Remove a record in table
     *
     * @param entity
     */
    @Override
    public boolean remove(final E entity) {
        LOG.info(new StringBuilder("[remove] Start: entity = ").append(entity));

        if (entity != null) {
            Session session = sessionFactory.openSession();
            try {
                session.beginTransaction();
                session.delete(entity);
                session.getTransaction().commit();
                session.close();
                LOG.info("[remove] End");
                return true;

            } catch (HibernateException e) {
                LOG.error(new StringBuilder("[remove] Error: ").append(e.getMessage()));
                session.getTransaction().rollback();
                LOG.info("[remove] End");
                return false;
            }
        }
        LOG.info("[remove] End");
        return false;
    }
}
