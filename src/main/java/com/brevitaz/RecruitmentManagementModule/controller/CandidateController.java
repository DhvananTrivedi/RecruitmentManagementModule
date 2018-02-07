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
    public boolean registerCandidate(@RequestBody Candidate candidate){
        System.out.println("Candidate is registered successfully.");
        return true;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List getAll() throws IOException{
        System.out.println("All received resumes will be shown.");
        return null;
    }

    @RequestMapping(value = "/{keyword}",method = RequestMethod.GET)
    public List findCandidateByKeyword(@PathVariable String keyword){
        System.out.println("Resumes will be selected according to the keyword -"+keyword);
        return null;
    }


    @RequestMapping(value = "/schedule-interview", method = RequestMethod.POST)
    public boolean addSchedule(@RequestBody Candidate candidate, Date date){
        System.out.println("Interview is scheduled. ");
        return true;
    }

    @RequestMapping(value = "/get-schedule" , method = {RequestMethod.GET})
    public List getSchedule()
    {
        System.out.println("Schedule list of all candidates");
        return null;
        // TODO - Today's schedule
    }

    @RequestMapping(value = "/schedule-interview/{scheduleId}" , method = {RequestMethod.PUT})
    public boolean updateScheduleOfCandidate(@RequestBody Candidate candidate, @PathVariable String scheduleId)
    {
        System.out.println("schedule is updated successfully!");
        return true;
    }


    @RequestMapping(value = "/status/{candidateId}" , method = {RequestMethod.GET})
    public String getCandidateStatus(@PathVariable String candidateId ,Candidate candidate)
    {
        System.out.println("Candidate Status of "+candidateId+" will be fetched.");
        return null;
    }

}

