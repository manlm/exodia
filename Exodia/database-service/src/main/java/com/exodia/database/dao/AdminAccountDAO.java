package com.exodia.database.dao;

import com.exodia.database.dao.common.GenericHibernateDAO;
import com.exodia.database.entity.AdminAccount;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by manlm1 on 9/11/2015.
 */
@Service
public class AdminAccountDAO extends GenericHibernateDAO<AdminAccount> {

    private static final Logger LOG = Logger.getLogger(AdminAccountDAO.class);

    @Autowired
    SessionFactory sessionFactory;

    /**
     * Find Admin Account by username
     *
     * @param username
     * @return
     */
    public AdminAccount findByUsername(String username) {
        LOG.info("[findByUsername] Start: username = " + username);

        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("from AdminAccount where username = :username ");
            query.setParameter("username", username);
            List<AdminAccount> list = query.list();
            if (list.size() > 0) {
                LOG.info("[findByUsername] End");
                return list.get(0);
            }
        } catch (QueryException e) {
            LOG.error("[findByUsername] QueryException:" + e.getMessage());
        } catch (HibernateException e) {
            LOG.error("[findByUsername] HibernateException:" + e.getMessage());
        } finally {
            session.close();
        }
        LOG.info("[findByUsername] End");
        return null;
    }

    /**
     * Find Admin Account by email
     *
     * @param email
     * @return
     */
    public AdminAccount findByEmail(String email) {
        LOG.info("[findByEmail] Start: email = " + email);

        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("from AdminAccount where admin_email = :email ");
            query.setParameter("email", email);
            List<AdminAccount> list = query.list();
            if (list.size() > 0) {
                LOG.info("[findByEmail] End");
                return list.get(0);
            }
        } catch (QueryException e) {
            LOG.error("[findByEmail] QueryException:" + e.getMessage());
        } catch (HibernateException e) {
            LOG.error("[findByEmail] HibernateException:" + e.getMessage());
        } finally {
            session.close();
        }
        LOG.info("[findByEmail] End");
        return null;
    }

    /**
     * Reset password of an account
     *
     * @param email
     * @return
     */
   /* public boolean resetPassword(String email) {
        AdminAccount adminAccount = findByEmail(email);
        adminAccount.setPassword(String.valueOf(PasswordUtil.generatePswd()));
        update(adminAccount);
        return false;
    }*/
}
