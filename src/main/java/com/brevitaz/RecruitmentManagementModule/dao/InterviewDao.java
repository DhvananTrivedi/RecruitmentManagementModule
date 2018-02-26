package com.brevitaz.RecruitmentManagementModule.dao;

import com.brevitaz.RecruitmentManagementModule.model.Interview;

import java.util.List;

public interface InterviewDao {


    public boolean delete(String id);
    public Interview getById(String id);
    public List<Interview> getAll();
    public boolean insert(Interview interview);
    public boolean update(String id , Interview interview);

    /*
    //TODO:This method is not required
    public List<Interview> getByName(String name);
    */
}
