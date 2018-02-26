package com.brevitaz.RecruitmentManagementModule.model;

import org.springframework.stereotype.Component;

/**
 * @author dhvanan on 7/2/18 Wednesday
 * @project RecruitmentManagementModule
 **/

@Component
public class Interviewer {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
