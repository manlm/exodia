package com.exodia.bom.service;

import com.exodia.bom.config.Properties;
import com.exodia.database.dao.PlayerAccountDAO;
import com.exodia.database.entity.PlayerAccount;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by manlm1 on 10/6/2015.
 */
@Service
public class DataService {

    private static final Logger LOG = Logger.getLogger(DataService.class);

    @Autowired
    private Properties properties;

    @Autowired
    private PlayerAccountDAO playerAccountDAO;

    public List<PlayerAccount> getAllPlayerAccount() {
        LOG.info("[getAllPlayerAccount] Start");
        LOG.info("[getAllPlayerAccount] End");
        return playerAccountDAO.getAll();
    }
}
