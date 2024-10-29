package com.java.service.impl;

import com.java.service.IJobService;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.spring.annotations.Recurring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author hieu.nt60
 * 10/25/2024
 */
@Service
public class JobServiceImpl implements IJobService {
    //    private static final Logger log = new JobRunrDashboardLogger(LoggerFactory.getLogger(JobServiceImpl.class));
    private static final Logger log = LoggerFactory.getLogger(JobServiceImpl.class);


    @Recurring(id = "job-test", cron = "${running.cycle.job.job-test}")
    @Job(name = "JOB TEST")
    @Override
    public String testJob() {
        return "Hello World";
    }
}
