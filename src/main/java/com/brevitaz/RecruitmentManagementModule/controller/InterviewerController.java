package com.brevitaz.RecruitmentManagementModule.controller;

import com.brevitaz.RecruitmentManagementModule.dao.InterviewerDao;
import com.brevitaz.RecruitmentManagementModule.model.Candidate;
import com.brevitaz.RecruitmentManagementModule.model.Interviewer;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/interviewer")
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


    @RequestMapping(value = "id/{id}",method = RequestMethod.GET)
    public Interviewer getById(@PathVariable String id){
        Interviewer interviewer = interviewerDao.getById(id);
        System.out.println("Interviewer with id : "+id+" is shown.");
        return interviewer;
    }


    @RequestMapping(value = "name/{name}",method = RequestMethod.GET)
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


//    QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", name)
//               .fuzziness(Fuzziness.AUTO)
//               .prefixLength(3)
//               .maxExpansions(10);


    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public boolean hello(){
        System.out.println("HELOLOLO");
        return true;
    }


}
