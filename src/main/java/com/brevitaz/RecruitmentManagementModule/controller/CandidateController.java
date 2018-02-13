package com.brevitaz.RecruitmentManagementModule.controller;

import com.brevitaz.RecruitmentManagementModule.model.Candidate;
import com.brevitaz.RecruitmentManagementModule.model.Openings;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author dhvanan on 2/2/18
 * @project RecruitmentManagementModule
 *
 **/

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @RequestMapping(method = RequestMethod.POST)
    public boolean register(@RequestBody Candidate candidate){
        System.out.println("Candidate is registered successfully.");
        return true;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Candidate> getAll() throws IOException{
        System.out.println("All received resumes will be shown.");
        return null;
    }

    @RequestMapping(value = "/id/{id}",method = RequestMethod.GET)
    public Candidate findCandidateById(@PathVariable String id){
        System.out.println("Resumes will be selected according to the keyword -"+id);
        return null;
    }

    @RequestMapping(value = "/name/{name}",method = RequestMethod.GET)
    public List<Candidate> findCandidateByName(@PathVariable String name){
        System.out.println("Resumes will be selected according to the keyword -"+name);
        return null;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public boolean update(@PathVariable String id,@RequestBody Candidate candidate){
        System.out.println("Candidate  at id "+id+" will be updated");
        return true;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String id ){
        System.out.println("Candidate will be deleted at id "+id);
        return true;
    }


    @RequestMapping(value = "/status/{candidateId}" , method = {RequestMethod.GET})
    public String getCandidateStatus(@PathVariable String candidateId ,Candidate candidate)
    {
        System.out.println("Candidate Status of "+candidateId+" will be fetched.");
        return null;
    }

}

