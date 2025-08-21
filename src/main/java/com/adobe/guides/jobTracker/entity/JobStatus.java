package com.adobe.guides.jobTracker.entity;

public enum JobStatus {
    APPLIED("Applied"),
    INTERVIEW_SCHEDULED("Interview Scheduled"),
    INTERVIEW_COMPLETED("Interview Completed"),
    OFFER_RECEIVED("Offer Received"),
    OFFER_ACCEPTED("Offer Accepted"),
    OFFER_DECLINED("Offer Declined"),
    REJECTED("Rejected"),
    WITHDRAWN("Withdrawn");
    
    private final String displayName;
    
    JobStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
