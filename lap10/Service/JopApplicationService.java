package com.example.lap10.Service;

import com.example.lap10.Model.JobApplication;
import com.example.lap10.Model.JobPost;
import com.example.lap10.Model.User;
import com.example.lap10.Repository.JobApplicationRepository;
import com.example.lap10.Repository.JobPostRepository;
import com.example.lap10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JopApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private  final  JobPostRepository jobPostRepository;


    public List<JobApplication> getAll(){
        return jobApplicationRepository.findAll();
    }

    public Boolean ApplyForJob(JobApplication jobApplication){
        //check if the user and job post exist
        boolean foundUser=false;
        boolean foundjob=false;
        for (User u:userRepository.findAll()){
            if(u.getId().equals(jobApplication.getUser_id()))
                foundUser=true;
        }
        for (JobPost j:jobPostRepository.findAll()){
            if(j.getId().equals(jobApplication.getJob_post_id()))
                foundjob=true;
        }

        if(foundUser && foundjob){
            jobApplicationRepository.save(jobApplication);
            return true;
        }
        return false;


    }



    public Boolean WithdrawJobApplication(Integer id){
        JobApplication jobApplication=jobApplicationRepository.getById(id);
        if(jobApplication==null)return false;

        jobApplicationRepository.delete(jobApplication);
        return true;
    }
}
