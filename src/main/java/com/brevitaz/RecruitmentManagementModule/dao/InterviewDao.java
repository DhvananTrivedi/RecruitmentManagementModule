package com.brevitaz.RecruitmentManagementModule.dao;

import com.brevitaz.RecruitmentManagementModule.model.Interview;

import java.util.List;

public interface InterviewDao {


    public boolean delete(String id);
    public Interview getById(String id);
    public List<Interview> getAll();
/**
 * @author dhvanan on 12/2/18 Monday
 * @project RecruitmentManagementModule
 **/
    boolean insert(Interview interview);
    List<Interview> getByName(String name);
    boolean update(String id , Interview interview);
}
