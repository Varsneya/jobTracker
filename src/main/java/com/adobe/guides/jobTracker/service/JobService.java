package com.adobe.guides.jobTracker.service;

import com.adobe.guides.jobTracker.entity.Job;
import com.adobe.guides.jobTracker.entity.JobStatus;
import com.adobe.guides.jobTracker.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    
    @Autowired
    private JobRepository jobRepository;
    
    // Create a new job application
    public Job createJob(Job job) {
        if (job.getAppliedDate() == null) {
            job.setAppliedDate(LocalDateTime.now());
        }
        return jobRepository.save(job);
    }
    
    // Get all jobs
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
    
    // Get job by ID
    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }
    
    // Update job
    public Job updateJob(Long id, Job jobDetails) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            job.setTitle(jobDetails.getTitle());
            job.setCompany(jobDetails.getCompany());
            job.setDescription(jobDetails.getDescription());
            job.setJobUrl(jobDetails.getJobUrl());
            job.setStatus(jobDetails.getStatus());
            job.setAppliedDate(jobDetails.getAppliedDate());
            return jobRepository.save(job);
        }
        throw new RuntimeException("Job not found with id: " + id);
    }
    
    // Delete job
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
    
    // Get jobs by status
    public List<Job> getJobsByStatus(JobStatus status) {
        return jobRepository.findByStatus(status);
    }
    
    // Search jobs by company
    public List<Job> searchJobsByCompany(String company) {
        return jobRepository.findByCompanyIgnoreCaseContaining(company);
    }
    
    // Search jobs by title
    public List<Job> searchJobsByTitle(String title) {
        return jobRepository.findByTitleIgnoreCaseContaining(title);
    }
    
    // Update job status
    public Job updateJobStatus(Long id, JobStatus status) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            job.setStatus(status);
            return jobRepository.save(job);
        }
        throw new RuntimeException("Job not found with id: " + id);
    }
    
    // Get job statistics
    public long getJobCountByStatus(JobStatus status) {
        return jobRepository.countByStatus(status);
    }
}
