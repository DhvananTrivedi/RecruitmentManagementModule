package com.brevitaz.RecruitmentManagementModule.controller;

import com.brevitaz.RecruitmentManagementModule.model.Interview;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interview")
public class InterviewController {
    @RequestMapping(method = RequestMethod.POST)
    public boolean add(@RequestBody Interview interview){
        System.out.println("Interview will be added ");
        return true;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Interview> getAll(){
        System.out.println("List of all interviews ");
        return null;
    }

    @RequestMapping(value = "/{candidateId}",method = RequestMethod.PUT)
    public boolean update(@PathVariable String candidateId , @RequestBody Interview interview){
        System.out.println("Interview will be updated at the specified candidate's id ");
        return true;
    }

    @RequestMapping(value = "/id/{candidateId}",method = RequestMethod.GET)
    public boolean getById(@PathVariable String candidateId){
        System.out.println("Get Interview by Id.");
        return true;
    }

    @RequestMapping(value = "/name/{candidateName}",method = RequestMethod.GET)
    public boolean getByName(@PathVariable String candidateName){
        System.out.println("Get Interviews by Name.");
        return true;
    }

    @RequestMapping(value = "/{candidateId}",method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String candidateId){
        System.out.println("Interview will be added ");
        return true;
    }


}
