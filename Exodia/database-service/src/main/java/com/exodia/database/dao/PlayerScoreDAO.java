package com.exodia.database.dao;

import com.exodia.database.dao.common.GenericHibernateDAO;
import com.exodia.database.entity.PlayerScore;
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
public class PlayerScoreDAO extends GenericHibernateDAO<PlayerScore> {

    private static final Logger LOG = Logger.getLogger(PlayerScoreDAO.class);

    @Autowired
    SessionFactory sessionFactory;

    /**
     * Get player score by Player Id
     *
     * @param playerId
     * @return
     */
    public List<PlayerScore> getByPlayerId(int playerId) {
        LOG.info(new StringBuilder("[getByPlayerId] Start: playerId = ").append(playerId));

        Session session = null;

        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(PlayerScore.class);
            criteria.add(Restrictions.like("playerId", playerId));
            if (criteria.list().size() > 0) {
                return criteria.list();
            }
        } catch (HibernateException e) {
            LOG.error(new StringBuilder("[getByPlayerId] HibernateException: ").append(e.getMessage()));
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
            LOG.info("[getByPlayerId] End");
        }
        LOG.info("[getByPlayerId] End");
        return null;
    }
}
