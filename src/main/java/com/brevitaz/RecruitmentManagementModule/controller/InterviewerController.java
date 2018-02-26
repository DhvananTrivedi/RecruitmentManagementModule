package com.brevitaz.RecruitmentManagementModule.controller;

import com.brevitaz.RecruitmentManagementModule.dao.InterviewerDao;
import com.brevitaz.RecruitmentManagementModule.model.Interviewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/interviewers")
public class InterviewerController {

    @Autowired
    InterviewerDao interviewerDao;

    @RequestMapping(method = RequestMethod.POST)
    public boolean register(@RequestBody Interviewer interviewer){
        boolean status = interviewerDao.insert(interviewer);
        System.out.println("Interviewer is registered successfully.");
        return status;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Interviewer> getAll() throws IOException {
        List<Interviewer> interviewers = interviewerDao.getAll();
        System.out.println("List of interviewers will be shown.");
        return interviewers;
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Interviewer getById(@PathVariable String id){
        Interviewer interviewer = interviewerDao.getById(id);
        System.out.println("Interviewer with id : "+id+" is shown.");
        return interviewer;
    }


    @RequestMapping(value = "/{name}",method = RequestMethod.GET)
    public List<Interviewer> getByName(@PathVariable String name){
        List<Interviewer> interviewers = interviewerDao.getByName(name);
        System.out.println("Interviewer : "+ name +" is shown.");
        return interviewers;
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String id){
        boolean status = interviewerDao.delete(id);
        System.out.println("Interviewer with id : "+id+" is deleted successfully !!");
        return status;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public boolean update(@PathVariable String id ,@RequestBody Interviewer interviewer){
        boolean status = interviewerDao.update(id,interviewer);
        System.out.println("Interviewer with id : "+id+" is updated successfully !!");
        return status;
    }

    //TODO: doubt for status
    @RequestMapping(value = "/status/{id}" , method = {RequestMethod.GET})
    public String getCandidateStatus(@PathVariable String candidateId)
    {
        System.out.println("Candidate Status of "+candidateId+" will be fetched.");
        return null;
    }


    @RequestMapping(value = "candidate/{id}" , method = {RequestMethod.GET})
    public List<Interviewer> getByCandidateId(@PathVariable String candidateId)
    {
        List<Interviewer> interviewers = interviewerDao.getByCandidateId(candidateId);
        System.out.println("List of interviewers for candidate with id - "+candidateId);
        return  interviewers;
    }

}
