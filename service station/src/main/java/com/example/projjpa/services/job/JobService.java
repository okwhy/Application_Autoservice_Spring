package com.example.projjpa.services.job;

import com.example.projjpa.models.Employee;
import com.example.projjpa.models.Job;

import java.util.List;
import java.util.Map;

public interface JobService {
    Map<Long,List<Job>> findothers(List<Job> jobs);
    Job add(Job job,Long id);
    Job add(Job job);
    Job get(Long id);
    String startJob(Long id);
    boolean existsJobsToFinish(Employee employee);
    List<Job> getJobsToDo(Employee employee);
    boolean existsJobsToDo(Employee employee);
    List<Job> getJobsToFinish(Employee employee);
    void finish(Job job,String desc);
}
