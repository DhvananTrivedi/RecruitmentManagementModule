package com.brevitaz.RecruitmentManagementModule.dao;

import com.brevitaz.RecruitmentManagementModule.model.Candidate;
import com.brevitaz.RecruitmentManagementModule.model.Interview;

import java.util.List;

public interface InterviewDao {


    public boolean insert(Candidate candidate);
    public boolean delete(String id);
    public Interview getById(String id);
    public List<Interview> getAll();
}
