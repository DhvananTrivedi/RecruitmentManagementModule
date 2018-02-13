package com.brevitaz.RecruitmentManagementModule.dao;

import com.brevitaz.RecruitmentManagementModule.model.Candidate;

import java.util.List;

/**
 * @author dhvanan on 8/2/18 Thursday
 * @project RecruitmentManagementModule
 **/
public interface CandidateDao {
    boolean insert(Candidate candidate);
    List<Candidate> getByName(String name);
    boolean update(String id , Candidate candidate);

}
