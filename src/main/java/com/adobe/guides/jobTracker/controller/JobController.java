package com.adobe.guides.jobTracker.controller;

import com.adobe.guides.jobTracker.entity.Job;
import com.adobe.guides.jobTracker.entity.JobStatus;
import com.adobe.guides.jobTracker.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {
    
    @Autowired
    private JobService jobService;
    
    // Create a new job application
    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job createdJob = jobService.createJob(job);
        return ResponseEntity.ok(createdJob);
    }
    
    // Get all jobs
    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }
    
    // Get job by ID
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Optional<Job> job = jobService.getJobById(id);
        if (job.isPresent()) {
            return ResponseEntity.ok(job.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Update job
    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job jobDetails) {
        try {
            Job updatedJob = jobService.updateJob(id, jobDetails);
            return ResponseEntity.ok(updatedJob);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Delete job
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok().build();
    }
    
    // Get jobs by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Job>> getJobsByStatus(@PathVariable JobStatus status) {
        List<Job> jobs = jobService.getJobsByStatus(status);
        return ResponseEntity.ok(jobs);
    }
    
    // Search jobs by company
    @GetMapping("/search/company")
    public ResponseEntity<List<Job>> searchJobsByCompany(@RequestParam String company) {
        List<Job> jobs = jobService.searchJobsByCompany(company);
        return ResponseEntity.ok(jobs);
    }
    
    // Search jobs by title
    @GetMapping("/search/title")
    public ResponseEntity<List<Job>> searchJobsByTitle(@RequestParam String title) {
        List<Job> jobs = jobService.searchJobsByTitle(title);
        return ResponseEntity.ok(jobs);
    }
    
    // Update job status
    @PatchMapping("/{id}/status")
    public ResponseEntity<Job> updateJobStatus(@PathVariable Long id, @RequestParam JobStatus status) {
        try {
            Job updatedJob = jobService.updateJobStatus(id, status);
            return ResponseEntity.ok(updatedJob);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Get job statistics
    @GetMapping("/stats/status/{status}")
    public ResponseEntity<Long> getJobCountByStatus(@PathVariable JobStatus status) {
        long count = jobService.getJobCountByStatus(status);
        return ResponseEntity.ok(count);
    }
    
    // Get all available job statuses
    @GetMapping("/statuses")
    public ResponseEntity<JobStatus[]> getAllJobStatuses() {
        return ResponseEntity.ok(JobStatus.values());
    }
}
