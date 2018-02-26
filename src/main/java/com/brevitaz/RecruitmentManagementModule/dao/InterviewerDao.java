package com.brevitaz.RecruitmentManagementModule.dao;

import com.brevitaz.RecruitmentManagementModule.model.Interviewer;

import java.util.List;

/**
 * @author dhvanan on 13/2/18 Tuesday
 * @project RecruitmentManagementModule
 **/
public interface InterviewerDao {

    public boolean insert(Interviewer interviewer);
    public List<Interviewer> getByName(String name);
    public boolean update(String id , Interviewer interviewer);
    public boolean delete(String id);
    public Interviewer getById(String id);
    public List<Interviewer> getAll();
    public List<Interviewer> getByCandidateId(String candidateId);



}
