package com.adobe.guides.jobTracker.repository;

import com.adobe.guides.jobTracker.entity.Job;
import com.adobe.guides.jobTracker.entity.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    
    // Find jobs by status
    List<Job> findByStatus(JobStatus status);
    
    // Find jobs by company name (case-insensitive)
    List<Job> findByCompanyIgnoreCaseContaining(String company);
    
    // Find jobs by title (case-insensitive)
    List<Job> findByTitleIgnoreCaseContaining(String title);
    
    // Find jobs by company and status
    List<Job> findByCompanyIgnoreCaseContainingAndStatus(String company, JobStatus status);
    
    // Custom query to find jobs applied in a date range
    @Query("SELECT j FROM Job j WHERE j.appliedDate BETWEEN :startDate AND :endDate")
    List<Job> findJobsAppliedBetween(@Param("startDate") java.time.LocalDateTime startDate, 
                                    @Param("endDate") java.time.LocalDateTime endDate);
    
    // Count jobs by status
    long countByStatus(JobStatus status);
}
