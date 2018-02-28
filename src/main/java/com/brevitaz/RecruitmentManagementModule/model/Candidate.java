package com.brevitaz.RecruitmentManagementModule.model;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author dhvanan on 2/2/18
 * @project RecruitmentManagementModule
 **/

@Component
public class Candidate {

    private String id;
    private String name;
    private String phoneNo;
    private List<String> skills;
    private String dataFromResume;
    private String status;
    private MultipartFile resume;
    private Interview interview;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getDataFromResume() {
        return dataFromResume;
    }

    public void setDataFromResume(String dataFromResume) {
        this.dataFromResume = dataFromResume;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Candidate(String id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public  Candidate(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Candidate candidate = (Candidate) o;

        if (id != null ? !id.equals(candidate.id) : candidate.id != null) return false;
        if (name != null ? !name.equals(candidate.name) : candidate.name != null) return false;
        if (phoneNo != null ? !phoneNo.equals(candidate.phoneNo) : candidate.phoneNo != null) return false;
        if (skills != null ? !skills.equals(candidate.skills) : candidate.skills != null) return false;
        if (dataFromResume != null ? !dataFromResume.equals(candidate.dataFromResume) : candidate.dataFromResume != null)
            return false;
        if (status != null ? !status.equals(candidate.status) : candidate.status != null) return false;
        if (resume != null ? !resume.equals(candidate.resume) : candidate.resume != null) return false;
        return interview != null ? interview.equals(candidate.interview) : candidate.interview == null;
    }


}

