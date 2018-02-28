package com.brevitaz.RecruitmentManagementModule.dao;


//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.brevitaz.RecruitmentManagementModule.model.Candidate;
import com.brevitaz.RecruitmentManagementModule.model.Interview;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InterviewDaoImplTest {

    @Autowired
    private InterviewDao interviewDao;

    @Test
    public void testInsert()
    {
        Candidate candidate = new Candidate();
        candidate.setId("1212");

        Interview interview = new Interview();
        interview.setId("1144");
        interview.setCandidate(candidate);

        System.out.println(interview);
        interviewDao.insert(interview);

        Interview interview1 = interviewDao.getById("1144");
        Assert.assertEquals(interview,interview1);

        interviewDao.delete("1144");
    }

    @Test
    public void testDelete()
    {
        Candidate candidate =new Candidate();
        candidate.setId("1212");
        Interview interview = new Interview();
        interview.setId("1144");
        interview.setCandidate(candidate);

        interviewDao.insert(interview);

        boolean status = interviewDao.delete("1144");
        Assert.assertEquals(true,status);

        try{

            Interview interview1 = interviewDao.getById("1144");
            Assert.assertEquals(true,false);

        }catch(NullPointerException ex)
        {
            System.out.println("This ID is arready deleted !!");
            Assert.assertEquals(true,true);
        }

    }

    @Test
    public void testUpdate()
    {
        Candidate candidate = new Candidate();
        candidate.setId("1111");
        Interview interview = new Interview();
        interview.setId("2233");
        interview.setCandidate(candidate);
        interviewDao.insert(interview);

        Candidate candidate1 = new Candidate();
        candidate.setId("2222");
        Interview interview1 = new Interview();
        interview1.setCandidate(candidate1);

        System.out.println("1 : "+interview);
        System.out.println("2 : "+interview1);

        boolean updateStatus = interviewDao.update("2233",interview1);
        Assert.assertEquals(true,updateStatus);

        Interview expectedInterview = interviewDao.getById("2233");
        System.out.println("expected : "+expectedInterview);

    }

    @Test
    public void testGetById()
    {
        Interview interview = new Interview();
        interview.setId("111222333");
        interviewDao.insert(interview);

        Interview expectedInterview = interviewDao.getById("111222333");
        Assert.assertEquals(expectedInterview,interview);

        interviewDao.delete("111222333");

    }

    @Test
    public void testGetAll()
    {
        Interview interview = new Interview();
        interview.setId("112233");
        interviewDao.insert(interview);

        Interview interview1 = new Interview();
        interview1.setId("445566");
        interviewDao.insert(interview1);

        List<Interview> interviews = interviewDao.getAll();
        Assert.assertThat(interviews,hasItems(interview,interview1));

        interviewDao.delete("112233");
        interviewDao.delete("445566");

    }

}