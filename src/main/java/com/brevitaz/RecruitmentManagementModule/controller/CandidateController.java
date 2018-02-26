package com.brevitaz.RecruitmentManagementModule.controller;

import com.brevitaz.RecruitmentManagementModule.dao.CandidateDao;
import com.brevitaz.RecruitmentManagementModule.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author dhvanan on 2/2/18
 * @project RecruitmentManagementModule
 *
 **/

@RestController
@RequestMapping("/candidates")
public class CandidateController {


    @Autowired
    CandidateDao candidateDao;

    @RequestMapping(method = RequestMethod.POST)
    public boolean register(@RequestBody Candidate candidate){
        boolean status = candidateDao.insert(candidate);
        System.out.println("Candidate is registered successfully.");
        return status;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Candidate> getAll() throws IOException{
        List<Candidate> candidates = candidateDao.getAll();
        System.out.println("All received resumes will be shown."+candidates);
        return candidates;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Candidate getById(@PathVariable String id){
        Candidate candidate = candidateDao.getById(id);
        System.out.println("Resumes will be selected according to candidate id -"+id);
        return candidate;
    }

    @RequestMapping(value = "/{name}",method = RequestMethod.GET)
    public List<Candidate> getByName(@PathVariable String name){
        List<Candidate> candidates =  candidateDao.getByName(name);
        System.out.println("Resumes will be selected according to the name of candidate -"+name);
        return candidates;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public boolean update(@PathVariable String id,@RequestBody Candidate candidate){
        boolean status = candidateDao.update(id, candidate);
        System.out.println("Candidate  at id "+id+" will be updated");
        return status;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String id ){
        boolean status = candidateDao.delete(id);
        System.out.println("Candidate will be deleted at id "+id);
        return status;
    }

    @RequestMapping(value = "/{keyword}",method = RequestMethod.GET)
    public List<Candidate> getByKeyword(@PathVariable String keyword ){
        List<Candidate> candidates = candidateDao.getByKeyword(keyword);
        System.out.println("Candidates will be shown at id "+keyword);
        return candidates;
    }


}

