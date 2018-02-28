package com.brevitaz.RecruitmentManagementModule.model;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author dhvanan on 7/2/18 Wednesday
 * @project RecruitmentManagementModule
 **/

@Component
public class Interview {

    private String id;
    private Date date;
    private Candidate candidate;
    private Interviewer interviewer;

   private enum type { Aptitude , Practical , TechnicalInterview , HR};
    private enum status {pass , fail};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interview interview = (Interview) o;

        if (id != null ? !id.equals(interview.id) : interview.id != null) return false;
        if (date != null ? !date.equals(interview.date) : interview.date != null) return false;
        if (candidate != null ? !candidate.equals(interview.candidate) : interview.candidate != null) return false;
        return interviewer != null ? interviewer.equals(interview.interviewer) : interview.interviewer == null;
    }


}
