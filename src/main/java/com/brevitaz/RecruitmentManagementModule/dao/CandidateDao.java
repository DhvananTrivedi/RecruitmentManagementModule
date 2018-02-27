package com.brevitaz.RecruitmentManagementModule.dao;

import com.brevitaz.RecruitmentManagementModule.model.Candidate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dhvanan on 8/2/18 Thursday
 * @project RecruitmentManagementModule
 **/

public interface CandidateDao {

    public boolean insert(Candidate candidate);
    public boolean delete(String id);
    public Candidate getById(String id);
    public List<Candidate> getAll();
    public List<Candidate> getByKeyword(String name);
    public List<Candidate> getByName(String name);
    public boolean update(String id , Candidate candidate);

}
