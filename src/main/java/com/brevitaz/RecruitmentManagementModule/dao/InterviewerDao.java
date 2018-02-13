package com.brevitaz.RecruitmentManagementModule.dao;

import com.brevitaz.RecruitmentManagementModule.model.Interviewer;

import java.util.List;

/**
 * @author dhvanan on 13/2/18 Tuesday
 * @project RecruitmentManagementModule
 **/
public interface InterviewerDao {
    boolean insert(Interviewer interviewer);
    List<Interviewer> getByName(String name);
    boolean update(String id , Interviewer interviewer);
}