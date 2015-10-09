package com.exodia.back.job;

import com.exodia.back.task.PlayerAccountTask;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by manlm1 on 10/9/2015.
 */
public class PlayerAccountJob extends QuartzJobBean {

    private static final Logger LOG = Logger.getLogger(PlayerAccountJob.class);

    private PlayerAccountTask playerAccountTask;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG.info("[executeInternal] Start");
        playerAccountTask.doDeletePlayer();
        LOG.info("[executeInternal] End");
    }

    public void setPlayerAccountTask(PlayerAccountTask playerAccountTask) {
        this.playerAccountTask = playerAccountTask;
    }
}
