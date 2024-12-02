package com.example.lap10.Service;

import com.example.lap10.Model.JobPost;
import com.example.lap10.Model.User;
import com.example.lap10.Repository.JobPostRepository;
import com.example.lap10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JopPostService {

    private final JobPostRepository jobPostRepository;

    public List<JobPost> getAll(){
        return jobPostRepository.findAll();
    }

    public void add(JobPost jobPost){
        jobPostRepository.save(jobPost);
    }

    public Boolean update(Integer id,JobPost jobPost){
        JobPost originalPost=jobPostRepository.getById(id);
        if(originalPost==null)return  false;
      originalPost.setTitle(jobPost.getTitle());
      originalPost.setDescription(jobPost.getDescription());
      originalPost.setLocation(jobPost.getLocation());
      originalPost.setSalary(jobPost.getSalary());

        jobPostRepository.save(originalPost);
        return true;
    }

    public Boolean delete(Integer id){
        JobPost jobPost=jobPostRepository.getById(id);
        if(jobPost==null)return false;

        jobPostRepository.delete(jobPost);
        return true;
    }

 
}
