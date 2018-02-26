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
}
