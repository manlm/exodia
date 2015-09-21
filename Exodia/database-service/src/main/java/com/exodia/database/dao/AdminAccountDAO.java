package com.exodia.database.dao;

import com.exodia.common.util.PasswordUtil;
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

    public List<AdminAccount> getAll() {
        LOG.info("[getAll] Start");
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("from AdminAccount");
            List<AdminAccount> list = query.list();
            if (list.size() > 0) {
                return list;
            }
        } catch (QueryException e) {
            LOG.error(new StringBuilder("[getAll] QueryException: ").append(e.getMessage()));
        } catch (HibernateException e) {
            LOG.error(new StringBuilder("[getAll] HibernateException: ").append(e.getMessage()));
        } finally {
            session.close();
        }
        LOG.info("[getAll] End");
        return null;
    }

    /**
     * Find Admin Account by username
     *
     * @param username
     * @return
     */
    public AdminAccount findByUsername(String username) {
        LOG.info(new StringBuilder("[findByUsername] Start: username = ").append(username));

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
            LOG.error(new StringBuilder("[findByUsername] QueryException: ").append(e.getMessage()));
        } catch (HibernateException e) {
            LOG.error(new StringBuilder("[findByUsername] HibernateException: ").append(e.getMessage()));
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
        LOG.info(new StringBuilder("[findByEmail] Start: email = ").append(email));

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
            LOG.error(new StringBuilder("[findByEmail] QueryException: ").append(e.getMessage()));
        } catch (HibernateException e) {
            LOG.error(new StringBuilder("[findByEmail] HibernateException: ").append(e.getMessage()));
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
    public void resetPassword(String email) {
        LOG.info(new StringBuilder("[resetPassword] Start: email = ").append(email));

        AdminAccount adminAccount = findByEmail(email);
        adminAccount.setPassword(String.valueOf(PasswordUtil.generatePswd()));
        adminAccount.setLastUpdate(System.currentTimeMillis());
        update(adminAccount);

        LOG.info("[resetPassword] End");
    }
}
