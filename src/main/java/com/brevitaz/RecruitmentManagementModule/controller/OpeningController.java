package com.brevitaz.RecruitmentManagementModule.controller;

import com.brevitaz.RecruitmentManagementModule.model.Openings;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dhvanan on 7/2/18 Wednesday
 * @project RecruitmentManagementModule
 **/
@RestController
@RequestMapping("/openings")
public class OpeningController {
    @RequestMapping(method = RequestMethod.GET)
    public List getAll(){
        System.out.println("All openings.");
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public boolean add(){
        System.out.println("Add openings.");
        return true;
    }

    @RequestMapping(value = "/{openingId}", method = RequestMethod.PUT)
    public void updateOpening(@RequestBody Openings openings){
        System.out.println("Update opening");

    }




}
