package com.brevitaz.RecruitmentManagementModule.model;

import java.util.Date;

/**
 * @author dhvanan on 7/2/18 Wednesday
 * @project RecruitmentManagementModule
 **/
public class Interview {
    Date date;
    Candidate candidate;
    Interviewer interviewer;

    

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Interviewer getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(Interviewer interviewer) {
        this.interviewer = interviewer;
    }



}
