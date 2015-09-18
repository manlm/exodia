package com.exodia.database.dao;

import com.exodia.database.dao.common.GenericHibernateDAO;
import com.exodia.database.entity.UserRoles;
import org.apache.log4j.Logger;
import org.hibernate.*;
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

    public String findById(int id) {
        LOG.info(new StringBuilder("[findById] Start: id = ").append(id));

        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("from UserRoles where id = :id ");
            query.setParameter("id", id);
            List<UserRoles> list = query.list();
            if (list.size() > 0) {
                LOG.info("[findById] End");
                return list.get(0).getRole();
            }
        } catch (QueryException e) {
            LOG.error(new StringBuilder("[findById] QueryException: ").append(e.getMessage()));
        } catch (HibernateException e) {
            LOG.error(new StringBuilder("[findById] HibernateException: ").append(e.getMessage()));
        } finally {
            session.close();
        }
        LOG.info("[findById] End");
        return null;
    }
}
