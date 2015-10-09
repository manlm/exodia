package com.exodia.database.dao;

import com.exodia.common.constant.Constant;
import com.exodia.common.util.DateTimeUtil;
import com.exodia.database.dao.common.GenericHibernateDAO;
import com.exodia.database.entity.AdminAccount;
import com.exodia.database.entity.UserRoles;
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
 * Created by manlm1 on 9/11/2015.
 */
@Service
public class AdminAccountDAO extends GenericHibernateDAO<AdminAccount> {

    private static final Logger LOG = Logger.getLogger(AdminAccountDAO.class);

    @Autowired
    SessionFactory sessionFactory;

    /**
     * Get all Admin Account
     *
     * @return
     */
    public List<AdminAccount> getAll() {
        LOG.info("[getAll] Start");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(AdminAccount.class);
            criteria.addOrder(Order.desc("creationTime"));
            return criteria.list();
        } catch (HibernateException e) {
            LOG.error(new StringBuilder("[getAll] HibernateException: ").append(e.getMessage()));
            LOG.info("[getAll] End");
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
            LOG.info("[getAll] End");
        }
    }

    /**
     * Find Admin Account by username
     *
     * @param username
     * @return
     */
    public AdminAccount getByUsername(String username) {
        LOG.info(new StringBuilder("[getByUsername] Start: username = ").append(username));

        Session session = null;

        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(AdminAccount.class);
            criteria.add(Restrictions.eq("username", username));
            if (criteria.list().size() > 0) {
                return (AdminAccount) criteria.list().get(0);
            }
        } catch (HibernateException e) {
            LOG.error(new StringBuilder("[getByUsername] HibernateException: ").append(e.getMessage()));
            session.getTransaction().rollback();
            LOG.info("[getByUsername] End");
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        LOG.info("[getByUsername] End");
        return null;
    }

    /**
     * Find Admin Account by email
     *
     * @param email
     * @return
     */
    public AdminAccount getByEmail(String email) {
        LOG.info(new StringBuilder("[getByEmail] Start: email = ").append(email));

        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(AdminAccount.class);
            criteria.add(Restrictions.like("email", String.valueOf(new StringBuilder("%").append(email).append("%"))));
            if (criteria.list().size() > 0) {
                return (AdminAccount) criteria.list().get(0);
            }
        } catch (HibernateException e) {
            LOG.error(new StringBuilder("[getByEmail] HibernateException: ").append(e.getMessage()));
            LOG.info("[getByEmail] End");
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        LOG.info("[getByEmail] End");
        return null;
    }

    /**
     * Get list Admin Account by all fields
     *
     * @param username
     * @param email
     * @param role
     * @param status
     * @return
     */
    public List<AdminAccount> getByConditions(String username, String email, String role, String status) {

        LOG.info(new StringBuilder("[getByConditions] Start: username = ").append(username)
                .append(", email = ").append(email)
                .append(", role = ").append(role)
                .append(", status = ").append(status));

        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(AdminAccount.class);

            if (!username.equals("")) {
                criteria.add(Restrictions.like("username", String.valueOf(new StringBuilder("%").append(username).append("%"))));
            }

            if (!email.equals("")) {
                criteria.add(Restrictions.like("email", String.valueOf(new StringBuilder("%").append(email).append("%"))));
            }

            if (!role.equals("")) {
                if (role.equalsIgnoreCase(Constant.ADMIN_ROLE.ACCOUNT_MANAGER.getValue())) {
                    UserRoles userRoles = new UserRoles();
                    userRoles.setId(Constant.ADMIN_ROLE_ID.ACCOUNT_MANAGER.getValue());
                    userRoles.setRole(Constant.ADMIN_ROLE.ACCOUNT_MANAGER.getValue());
                    criteria.add(Restrictions.eq("role", userRoles));
                } else if (role.equalsIgnoreCase(Constant.ADMIN_ROLE.DATA_MANAGER.getValue())) {
                    UserRoles userRoles = new UserRoles();
                    userRoles.setId(Constant.ADMIN_ROLE_ID.DATA_MANAGER.getValue());
                    userRoles.setRole(Constant.ADMIN_ROLE.DATA_MANAGER.getValue());
                    criteria.add(Restrictions.eq("role", userRoles));
                }
            }

            if (!status.equals("")) {
                if (status.equalsIgnoreCase(Constant.STATUS.ACTIVE.getValue())) {
                    UserStatus userStatus = new UserStatus();
                    userStatus.setId(Constant.STATUS_ID.ACTIVE.getValue());
                    userStatus.setStatus(Constant.STATUS.ACTIVE.getValue());
                    criteria.add(Restrictions.eq("status", userStatus));
                } else if (status.equalsIgnoreCase(Constant.STATUS.INACTIVE.getValue())) {
                    UserStatus userStatus = new UserStatus();
                    userStatus.setId(Constant.STATUS_ID.INACTIVE.getValue());
                    userStatus.setStatus(Constant.STATUS.INACTIVE.getValue());
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
            LOG.info("[getByConditions] End");
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Get Admin Account by Status
     *
     * @param status
     * @return
     */
    public List<AdminAccount> getByStatus(int status) {
        LOG.info(new StringBuilder("[getByStatus] Start: status = ").append(status));

        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(AdminAccount.class);

            if (status == Constant.STATUS_ID.ACTIVE.getValue()) {
                UserStatus userStatus = new UserStatus();
                userStatus.setId(Constant.STATUS_ID.ACTIVE.getValue());
                userStatus.setStatus(Constant.STATUS.ACTIVE.getValue());
                criteria.add(Restrictions.eq("status", userStatus));
            } else if (status == Constant.STATUS_ID.INACTIVE.getValue()) {
                UserStatus userStatus = new UserStatus();
                userStatus.setId(Constant.STATUS_ID.INACTIVE.getValue());
                userStatus.setStatus(Constant.STATUS.INACTIVE.getValue());
                criteria.add(Restrictions.eq("status", userStatus));
            } else if (status == Constant.STATUS_ID.DELETED.getValue()) {
                UserStatus userStatus = new UserStatus();
                userStatus.setId(Constant.STATUS_ID.DELETED.getValue());
                userStatus.setStatus(Constant.STATUS.DELETED.getValue());
                criteria.add(Restrictions.eq("status", userStatus));
            }

            return criteria.list();
        } catch (HibernateException e) {
            LOG.error(new StringBuilder("[getByStatus] HibernateException: ").append(e.getMessage()));
            LOG.info("[getByStatus] End");
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
