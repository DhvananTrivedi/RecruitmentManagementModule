package com.brevitaz.RecruitmentManagementModule.dao;

import com.brevitaz.RecruitmentManagementModule.model.Interview;

import java.util.List;

/**
 * @author dhvanan on 12/2/18 Monday
 * @project RecruitmentManagementModule
 **/
public interface InterviewDao {
    boolean insert(Interview interview);
    List<Interview> getByName(String name);
    boolean update(String id , Interview interview);
}
