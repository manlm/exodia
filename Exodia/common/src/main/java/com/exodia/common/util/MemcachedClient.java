package com.exodia.common.util;

import com.exodia.common.config.Properties;
import net.spy.memcached.AddrUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by manlm1 on 9/13/2015.
 */
@Service
public class MemcachedClient {

    private static final Logger LOG = Logger.getLogger(MemcachedClient.class);

    private static net.spy.memcached.MemcachedClient cache;

    private Properties properties = new Properties();

    public MemcachedClient() {
        LOG.info("[MemcachedClient] Start");
        try {
            cache = new net.spy.memcached.MemcachedClient(AddrUtil.getAddresses(properties.getProperty("memcached.server")));
        } catch (IOException e) {
            LOG.error(new StringBuilder("[MemcachedClient] IOException: ").append(e.getMessage()));
        }
        LOG.info("[MemcachedClient] End");
    }

    /**
     * Get cache
     *
     * @param key
     * @param <E>
     * @return
     */
    public <E> E get(String key) {
        LOG.info(new StringBuilder("[get] Start: key = ").append(key));
        LOG.info("[get] End");
        return (E) cache.get(key);
    }

    /**
     * Set cache
     *
     * @param key
     * @param value
     * @param timeToIdle
     * @param <E>
     */
    public <E> void set(String key, E value, int timeToIdle) {
        LOG.info(new StringBuilder("[set] Start: key = ").append(key).append(", value = ").append(value)
                .append(", timeToIdle = ").append(timeToIdle));
        LOG.info("[set] End");
        cache.set(key, timeToIdle, value);
    }

    /**
     * Remove cache
     *
     * @param key
     * @return
     */
    public Boolean remove(String key) {
        LOG.info(new StringBuilder("[remove] Start: key = ").append(key));
        try {
            LOG.info("[remove] End");
            return cache.delete(key).get();
        } catch (InterruptedException e) {
            LOG.error(new StringBuilder("[remove] InterruptedException: ").append(e.getMessage()));
        } catch (ExecutionException e) {
            LOG.error(new StringBuilder("[remove] ExecutionException: ").append(e.getMessage()));
        }
        LOG.info("[remove] End");
        return false;
    }

    /**
     * Check if cache is exist
     *
     * @param key
     * @return
     */
    public Boolean isExits(String key) {
        LOG.info(new StringBuilder("[isExits] Start: key = ").append(key));
        LOG.info("[isExits] End");
        return cache.get(key) == null ? false : true;
    }
}
