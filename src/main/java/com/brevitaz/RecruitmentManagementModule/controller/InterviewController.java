package com.brevitaz.RecruitmentManagementModule.controller;

import com.brevitaz.RecruitmentManagementModule.dao.InterviewDao;
import com.brevitaz.RecruitmentManagementModule.model.Interview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interview")
public class InterviewController {

    @Autowired
    InterviewDao interviewDao;

    @RequestMapping(method = RequestMethod.POST)
    public boolean add(@RequestBody Interview interview){
        boolean status = interviewDao.insert(interview);
        System.out.println("Interview will be added ");
        return status;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Interview> getAll(){
        List<Interview> interviews = interviewDao.getAll();
        System.out.println("List of all interviews "+interviews);
        return interviews;
    }

    @RequestMapping(value = "/{candidateId}",method = RequestMethod.PUT)
    public boolean update(@PathVariable String candidateId , @RequestBody Interview interview){
        boolean status = interviewDao.update(candidateId,interview);
        System.out.println("Interview will be updated at the specified candidate's id ");
        return true;
    }

    @RequestMapping(value = "/id/{candidateId}",method = RequestMethod.GET)
    public boolean getById(@PathVariable String candidateId){
        Interview interview = interviewDao.getById(candidateId);
        System.out.println("Get Interview by Id."+interview.getCandidate().getName());
        return true;
    }

    @RequestMapping(value = "/name/{candidateName}",method = RequestMethod.GET)
    public List<Interview> getByName(@PathVariable String candidateName){
        List<Interview> interviews = interviewDao.getByName(candidateName);
        System.out.println("Get Interviews by Name."+interviews);
        return interviews;
    }

    @RequestMapping(value = "/{candidateId}",method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String candidateId){
        boolean status = interviewDao.delete(candidateId);
        System.out.println("Interview will be added ");
        return status;
    }


    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public boolean hello(){
        System.out.println("HELOLOLO");
        return true;
    }


}
