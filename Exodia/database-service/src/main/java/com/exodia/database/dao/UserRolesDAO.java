package com.exodia.database.dao;

import com.exodia.database.dao.common.GenericHibernateDAO;
import com.exodia.database.entity.UserRoles;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by manlm1 on 9/11/2015.
 */
@Service
public class UserRolesDAO extends GenericHibernateDAO<UserRoles> {

    private static final Logger LOG = Logger.getLogger(UserRolesDAO.class);

    @Autowired
    SessionFactory sessionFactory;

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
        } catch (QueryException e) {
            LOG.error(new StringBuilder("[findById] QueryException: ").append(e.getMessage()));
        } catch (HibernateException e) {
            LOG.error(new StringBuilder("[findById] HibernateException: ").append(e.getMessage()));
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
