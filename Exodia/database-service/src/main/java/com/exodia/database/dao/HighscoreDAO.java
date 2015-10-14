package com.exodia.database.dao;

import com.exodia.common.util.DateTimeUtil;
import com.exodia.database.dao.common.GenericHibernateDAO;
import com.exodia.database.entity.Highscore;
import com.exodia.database.entity.PlayerAccount;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manlm1 on 10/12/2015.
 */
@Service
public class HighscoreDAO extends GenericHibernateDAO<Highscore> {

    private static final Logger LOG = Logger.getLogger(HighscoreDAO.class);

    private static final String DATE_FORMAT = "yyyyMMdd";
    private static final String FIRST_DAY_OF_MONTH = "01";
    private static final String LAST_DAY_OF_MONTH_31 = "31";
    private static final String LAST_DAY_OF_MONTH_30 = "30";
    private static final String LAST_DAY_OF_FEB = "28";
    private static final String LAST_DAY_OF_FEB_LEAP = "29";
    private static final List<Integer> month31days = new ArrayList<>();
    private static final List<Integer> month30days = new ArrayList<>();

    @Autowired
    private SessionFactory sessionFactory;

    public HighscoreDAO() {
        month31days.add(1);
        month31days.add(3);
        month31days.add(5);
        month31days.add(7);
        month31days.add(8);
        month31days.add(10);
        month31days.add(12);

        month30days.add(4);
        month30days.add(6);
        month30days.add(9);
        month30days.add(11);
    }

    /**
     * Get highscore by player account
     *
     * @param playerAccount
     * @return
     */
    public Highscore getByPlayerAccount(PlayerAccount playerAccount) {
        LOG.info(new StringBuilder("[getByPlayerId] Start: playerAccount = ").append(playerAccount));

        Session session = null;

        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Highscore.class);
            criteria.add(Restrictions.eq("playerAccount", playerAccount));
            criteria.addOrder(Order.desc("score"));
            if (criteria.list().size() > 0) {
                return (Highscore) criteria.list().get(0);
            }
        } catch (HibernateException e) {
            LOG.error(new StringBuilder("[getByPlayerId] HibernateException: ").append(e.getMessage()));
            LOG.info("[getByPlayerId] End");
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        LOG.info("[getByPlayerId] End");
        return null;
    }

    /**
     * Generate yyyyMMdd String
     *
     * @param month
     * @param year
     * @param monthString
     * @return
     */
    public String generateDate(int month, int year, String monthString) {

        LOG.info(new StringBuilder("generateDate Start: month = ").append(month)
                .append(", year = ").append(month)
                .append(", monthString = ").append(monthString));

        String end = "";

        if (month == 2) {
            if (year % 2 == 0) {
                end = String.valueOf(new StringBuilder(String.valueOf(year)).append(monthString).append(LAST_DAY_OF_FEB_LEAP));
            } else {
                end = String.valueOf(new StringBuilder(String.valueOf(year)).append(monthString).append(LAST_DAY_OF_FEB));
            }
        } else if (month31days.contains(month)) {
            end = String.valueOf(new StringBuilder(String.valueOf(year)).append(monthString).append(LAST_DAY_OF_MONTH_31));
        } else if (month30days.contains(month)) {
            end = String.valueOf(new StringBuilder(String.valueOf(year)).append(monthString).append(LAST_DAY_OF_MONTH_30));
        }

        LOG.info("[generateDate] End");
        return end;
    }

    /**
     * Get player score of a month
     *
     * @param month
     * @param year
     * @return
     */
    public List<Highscore> getHighScoreOfMonth(int month, int year, int numberOfRecord) {

        LOG.info(new StringBuilder("[getHighScoreOfMonth] Start: month = ").append(month)
                .append(", year = ").append(year));

        String monthString = String.valueOf(month);

        if (month < 10) {
            monthString = "0" + month;
        }

        String begin = String.valueOf(new StringBuilder(String.valueOf(year)).append(monthString).append(FIRST_DAY_OF_MONTH));
        String end = generateDate(month, year, monthString);

        long beginMillis = DateTimeUtil.dateToMillis(begin, DATE_FORMAT);
        long endMillis = DateTimeUtil.dateToMillis(end, DATE_FORMAT);

        Session session = null;

        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Highscore.class);
            criteria.add(Restrictions.ge("playTime", beginMillis));
            criteria.add(Restrictions.le("playTime", endMillis));
            criteria.addOrder(Order.desc("score"));
            if (numberOfRecord != 0) {
                criteria.setMaxResults(numberOfRecord);
            }
            LOG.info("[getHighScoreOfMonth] End");
            return criteria.list();
        } catch (HibernateException e) {
            LOG.error(new StringBuilder("[getHighScoreOfMonth] HibernateException: ").append(e.getMessage()));
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}