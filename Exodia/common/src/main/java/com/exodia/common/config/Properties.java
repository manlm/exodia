package com.exodia.common.config;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by manlm1 on 9/13/2015.
 */
public class Properties {

    private static final String PROPERTIES_FILE_NAME = "common-config.properties";

    private static final Logger LOG = Logger.getLogger(Properties.class);

    private static java.util.Properties prop;

    InputStream inputStream;

    /**
     * Get property from properties file
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        LOG.info(new StringBuilder("[getProperty] Start: key = ").append(key));

        try {
            prop = new java.util.Properties();
            String propFileName = PROPERTIES_FILE_NAME;

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            }

            LOG.info("[getProperty] End");
            return prop.getProperty(key);
        } catch (IOException e) {
            LOG.error(new StringBuilder("[getProperty] IOException: ").append(e.getMessage()));
        }
        LOG.info("[getProperty] End");
        return null;
    }
}
