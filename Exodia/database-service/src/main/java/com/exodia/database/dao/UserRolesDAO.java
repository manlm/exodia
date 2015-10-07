package com.exodia.database.dao;

import com.exodia.database.dao.common.GenericHibernateDAO;
import com.exodia.database.entity.UserRoles;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by manlm1 on 9/11/2015.
 */
@Service
public class UserRolesDAO extends GenericHibernateDAO<UserRoles> {

    private static final Logger LOG = Logger.getLogger(UserRolesDAO.class);

    @Autowired
    SessionFactory sessionFactory;

    /**
     * Get all user roles
     *
     * @return
     */
    public List<UserRoles> getAll() {
        LOG.info("[getAll] Start");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(UserRoles.class);
            return criteria.list();
        } catch (HibernateException e) {
            LOG.error(new StringBuilder("[getAll] HibernateException: ").append(e.getMessage()));
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
            LOG.info("[getAll] End");
        }
        LOG.info("[getAll] End");
        return null;
    }

    /**
     * Get role by Id
     *
     * @param id
     * @return
     */
    public String findById(int id) {
        LOG.info(new StringBuilder("[findById] Start: id = ").append(id));

        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(UserRoles.class);
            criteria.add(Restrictions.eq("id", id));
            if (criteria.list().size() > 0) {
                return ((UserRoles) criteria.list().get(0)).getRole();
            }
        } catch (HibernateException e) {
            LOG.error(new StringBuilder("[findById] HibernateException: ").append(e.getMessage()));
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
            LOG.info("[findById] End");
        }
        LOG.info("[findById] End");
        return null;
    }
}
