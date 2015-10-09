package com.exodia.database.dao;

import com.exodia.common.constant.Constant;
import com.exodia.database.dao.common.GenericHibernateDAO;
import com.exodia.database.entity.PlayerAccount;
import com.exodia.database.entity.UserStatus;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by manlm1 on 9/23/2015.
 */
@Service
public class PlayerAccountDAO extends GenericHibernateDAO<PlayerAccount> {

    private static final Logger LOG = Logger.getLogger(PlayerAccountDAO.class);

    @Autowired
    SessionFactory sessionFactory;

    /**
     * Get all Player Account
     *
     * @return
     */
    public List<PlayerAccount> getAll() {
        LOG.info("[getAll] Start");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(PlayerAccount.class);
            criteria.addOrder(Order.desc("creationTime"));
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
     * Find Player Account by email
     *
     * @param email
     * @return
     */
    public PlayerAccount getByEmail(String email) {
        LOG.info(new StringBuilder("[getByEmail] Start: email = ").append(email));

        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(PlayerAccount.class);
            criteria.add(Restrictions.eq("email", email));
            if (criteria.list().size() > 0) {
                return (PlayerAccount) criteria.list().get(0);
            }
        } catch (HibernateException e) {
            LOG.error(new StringBuilder("[getByEmail] HibernateException: ").append(e.getMessage()));
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
            LOG.info("[getByEmail] End");
        }
        LOG.info("[getByEmail] End");
        return null;
    }

    /**
     * Get list Player Account by all fields
     *
     * @param email
     * @param status
     * @return
     */
    public List<PlayerAccount> getByConditions(String email, String status) {

        LOG.info(new StringBuilder("[getByConditions] Start: email = ").append(email)
                .append(", status = ").append(status));

        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(PlayerAccount.class);

            if (!email.equals("")) {
                criteria.add(Restrictions.like("email"
                        , String.valueOf(new StringBuilder("%").append(email).append("%"))));
            }

            if (!status.equals("")) {
                if (status.equalsIgnoreCase(Constant.STATUS.ACTIVE.getValue())) {
                    UserStatus userStatus = new UserStatus();
                    userStatus.setId(Constant.STATUS_ID.ACTIVE.getValue());
                    userStatus.setStatus(Constant.STATUS.ACTIVE.getValue());
                    criteria.add(Restrictions.eq("status", userStatus));
                } else if (status.equalsIgnoreCase(Constant.STATUS.DELETED.getValue())) {
                    UserStatus userStatus = new UserStatus();
                    userStatus.setId(Constant.STATUS_ID.DELETED.getValue());
                    userStatus.setStatus(Constant.STATUS.DELETED.getValue());
                    criteria.add(Restrictions.eq("status", userStatus));
                }
            }

            return criteria.list();

        } catch (HibernateException e) {
            LOG.error(new StringBuilder("[getByConditions] HibernateException: ").append(e.getMessage()));
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.cancelQuery();
            }
            LOG.info("[getByConditions] End");
        }
        LOG.info("[getByConditions] End");
        return null;
    }
}
