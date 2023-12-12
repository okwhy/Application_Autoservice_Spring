package com.example.projjpa.services.job;

import com.example.projjpa.models.Employee;
import com.example.projjpa.models.Job;
import com.example.projjpa.repos.JobJpaRepo;
import com.example.projjpa.repos.OrderJpaRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Service
public class JobServiceImp implements JobService{
    private final OrderJpaRepo orderJpaRepo;
    private final JobJpaRepo jobJpaRepo;

    @Override
    public Map<Long, List<Job>> findothers(List<Job> jobs) {
        Map<Long,List<Job>> res=new HashMap<>();
        for(Job j:jobs){
            res.put(j.getId(),jobJpaRepo.findByOrderNumAndIdNot(j.getOrderNum(),j.getId()));
        }
        return res;
    }

    @Override
    public Job add(Job job,Long id) {
        job.setOrderNum(orderJpaRepo.findById(id).orElseThrow());
        job.setStarted(false);
        return jobJpaRepo.save(job);
    }

    @Override
    public Job add(Job job) {
        return jobJpaRepo.save(job);
    }

    @Override
    public Job get(Long id) {
        return jobJpaRepo.findById(id).orElseThrow();
    }

    @Override
    public String startJob(Long id) {
        Job job=this.get(id);
        job.setStarted(true);
        return jobJpaRepo.save(job).getEmployee().getId()+"";
    }

    @Override
    public boolean existsJobsToFinish(Employee employee) {
        return jobJpaRepo.existsByStartedAndDescriptionNullAndEmployee(true, employee);
    }

    @Override
    public List<Job> getJobsToDo(Employee employee) {
        return jobJpaRepo.findByStartedAndDescriptionNullAndEmployee(false, employee);
    }

    @Override
    public boolean existsJobsToDo(Employee employee) {
        return jobJpaRepo.existsByStartedAndDescriptionNullAndEmployee(false, employee);
    }

    @Override
    public List<Job> getJobsToFinish(Employee employee) {
        return jobJpaRepo.findByStartedAndDescriptionNullAndEmployee(true, employee);
    }

    @Override
    public void finish(Job job, String desc) {
//        desc.replace("\n", "");
        if(desc==null||desc.isBlank()){
            desc="-";
        }
        job.setDescription(desc);
        jobJpaRepo.save(job);
    }
}
