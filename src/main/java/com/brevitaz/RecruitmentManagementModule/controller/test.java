/*
package com.brevitaz.RecruitmentManagementModule.controller;
import com.brevitaz.RecruitmentManagementModule.model.Applicant;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/recruit-test")
public class test {


    @RequestMapping(method = {RequestMethod.POST})
    public boolean registerCandidate(@RequestBody Applicant applicant)
    {
        System.out.println("Applicant is registered successfully !!");
        return true;
    }

    @RequestMapping(method = {RequestMethod.GET})
    public List getAllCVs() throws IOException {

        System.out.println("Opened CVs");
        return null;
    }

    @RequestMapping(value="/get-by-keyword" , method = {RequestMethod.GET})
    public List getCVsByKeyword(@PathVariable String keyword) throws IOException {

        System.out.println(keyword + "CVs");
        return null;
    }

    @RequestMapping(value = "/openings" , method = {RequestMethod.GET})
    public List allOpenings()
    {
        System.out.println("All Openings");
        return null;
    }

    @RequestMapping(value = "/openings/update" , method = {RequestMethod.PUT})
    public boolean updateOpenings(@RequestBody String field , int numberOfCandidates)
    {
        System.out.println("Opening will be updated for "+field+" field and "+numberOfCandidates+" candidates");
        return true;
    }

    @RequestMapping(value = "/schedule-appointment" , method = {RequestMethod.POST})
    public boolean addSchedule(@RequestBody Applicant applicant, String date , String time)
    {
        System.out.println("Interview is scheduled");
        return true;
    }

    @RequestMapping(value = "/get-schedule" , method = {RequestMethod.GET})
    public List getSchedule()
    {
        System.out.println("Schedule list of all candidates");
        return null;
    }

    @RequestMapping(value = "/schedule-appointment/update" , method = {RequestMethod.PUT})
    public boolean updateScheduleOfCandidate(@RequestBody Applicant applicant, String date , String time)
    {
        System.out.println("schedule is upadated successfully!");
        return true;
    }

    @RequestMapping(value = "/get-applicant-status" , method = {RequestMethod.GET})
    public ApplicantStatus getApplicantStatus(@RequestBody String id , String password)
    {
        System.out.println("Applicant Status");
        return null;
    }

}

*/
