package com.brevitaz.RecruitmentManagementModule.controller;

import com.brevitaz.RecruitmentManagementModule.model.Candidate;
import com.brevitaz.RecruitmentManagementModule.model.Interviewer;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/interviewer")
public class InterviewerController {



    @RequestMapping(method = RequestMethod.POST)
    public boolean register(@RequestBody Interviewer interviewer){
        System.out.println("Interviewer is registered successfully.");
        return true;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Interviewer> getAll() throws IOException {
        System.out.println("List of interviewers will be shown.");
        return null;
    }


    @RequestMapping(value = "id/{id}",method = RequestMethod.GET)
    public Interviewer getById(@PathVariable String id){
        System.out.println("Interviewer with id : "+id+" is shown.");
        return null;
    }


    @RequestMapping(value = "name/{name}/",method = RequestMethod.GET)
    public List<Interviewer> getByName(@PathVariable String name){
        System.out.println("Interviewer : "+ name +" is shown.");
        return null;
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String id){
        System.out.println("Interviewer with id : "+id+" is deleted successfully !!");
        return true;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public boolean update(@PathVariable String id){
        System.out.println("Interviewer with id : "+id+" is updated successfully !!");
        return true;
    }



}
