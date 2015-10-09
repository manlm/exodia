package com.exodia.database.dao;

import com.exodia.database.dao.common.GenericHibernateDAO;
import com.exodia.database.entity.AdminAccessLog;
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
 * Created by manlm1 on 10/2/2015.
 */
@Service
public class AdminAccessLogDAO extends GenericHibernateDAO<AdminAccessLog> {

    private static final Logger LOG = Logger.getLogger(AdminAccessLogDAO.class);

    @Autowired
    SessionFactory sessionFactory;

    /**
     * Get access log of an admin account by Id
     *
     * @param adminId
     * @return
     */
    public List<AdminAccessLog> getByAdminId(int adminId) {
        LOG.info(new StringBuilder("[getByAdminId] Start: adminId = ").append(adminId));

        Session session = null;

        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(AdminAccessLog.class);
            criteria.add(Restrictions.eq("adminId", adminId));
            if (criteria.list().size() > 0) {
                return criteria.list();
            }
        } catch (HibernateException e) {
            LOG.error(new StringBuilder("[getByAdminId] HibernateException: ").append(e.getMessage()));
            LOG.info("[getByAdminId] End");
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        LOG.info("[getByAdminId] End");
        return null;
    }
}
