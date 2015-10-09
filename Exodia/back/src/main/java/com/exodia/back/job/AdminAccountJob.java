package com.exodia.back.job;

import com.exodia.back.task.AdminAccountTask;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class AdminAccountJob extends QuartzJobBean {

    private static final Logger LOG = Logger.getLogger(AdminAccountJob.class);

    private AdminAccountTask adminAccountTask;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG.info("[executeInternal] Start");
        adminAccountTask.doDeleteAdmin();
        LOG.info("[executeInternal] End");
    }

    public void setAdminAccountTask(AdminAccountTask adminAccountTask) {
        this.adminAccountTask = adminAccountTask;
    }
}