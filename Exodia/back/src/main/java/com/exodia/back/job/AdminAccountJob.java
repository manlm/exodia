package com.exodia.back.job;

import com.exodia.back.task.AdminAccountTask;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class AdminAccountJob extends QuartzJobBean {

    private AdminAccountTask adminAccountTask;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        adminAccountTask.doDeleteAdmin();
    }

    public void setAdminAccountTask(AdminAccountTask adminAccountTask) {
        this.adminAccountTask = adminAccountTask;
    }
}