package com.brevitaz.RecruitmentManagementModule.dao;

import com.brevitaz.RecruitmentManagementModule.model.Interviewer;

import java.util.List;

public interface InterviewerDao {


    public boolean insert(Interviewer interviewer);
    public boolean delete(String id);
    public Interviewer getById(String id);
    public List<Interviewer> getAll();


}
