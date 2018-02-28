package com.brevitaz.RecruitmentManagementModule.dao;

import com.brevitaz.RecruitmentManagementModule.model.Candidate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;

/**
 * @author dhvanan on 28/2/18 Wednesday
 * @project RecruitmentManagementModule
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CandidateDaoImplTest {

    @Autowired
    CandidateDao candidateDao;

    @Test
    public void testInsert(){
        // init
        Candidate candidate = new Candidate("123","John Doe","pending");
        ///  exec
        boolean status = candidateDao.insert(candidate);
        // assertion
        Candidate actual  = candidateDao.getById("123");
        Assert.assertEquals(candidate,actual);
        candidateDao.delete("1");
    }

    @Test
    public void testDelete() {
        // init
        Candidate candidate = new Candidate("1","Dhvanan","pending");
        boolean status = candidateDao.insert(candidate);

        //exec
        boolean deleteStatus = candidateDao.delete("1");

        ///assertion
        try{
            Candidate candidate1 = candidateDao.getById("1");
            Assert.assertEquals(true,false);
        }
        catch (NullPointerException npe){
            Assert.assertEquals(true,true);
        }
        Assert.assertEquals(true, status);
    }

    @Test
    public void testUpdate() {
        // init
        Candidate dhvanan = new Candidate("1","Dhvanan","pending");
        candidateDao.insert(dhvanan);

        //exec
        Candidate candidate = new Candidate();
        candidate.setStatus("scheduled");
        boolean status = candidateDao.update("1", candidate);

        ///assertion
        Candidate actual = candidateDao.getById("1");
        Assert.assertEquals("scheduled",actual.getStatus());
        candidateDao.delete("1");
    }

    @Test
    public void testGetAll() {
        // init
        Candidate dhvanan = new Candidate("1","Dhvanan","pending");
        candidateDao.insert(dhvanan);

        Candidate adhishree = new Candidate("2","Adhishree","pending");
        candidateDao.insert(adhishree);

        Candidate ankit = new Candidate("3","Ankit","pending");
        candidateDao.insert(ankit);

        //exec
        List<Candidate> candidates = candidateDao.getAll();
        Assert.assertThat(candidates,hasItems(dhvanan,adhishree,ankit));
        Assert.assertNotNull(candidates);
        candidateDao.delete("1");
        candidateDao.delete("2");
        candidateDao.delete("3");
    }

    @Test
    public void testGetById() {
        // init
        Candidate dhvanan = new Candidate("1","Dhvanan","pending");
        candidateDao.insert(dhvanan);

        // exec
        Candidate actualCandidate = candidateDao.getById("1");
        // assertion
        Assert.assertEquals(dhvanan,actualCandidate);
        Assert.assertNotNull(actualCandidate);
        candidateDao.delete("1");
    }


    @Test
    public void testGetByName() {
        // init asfsfsafafasdffs
        Candidate dhvanan = new Candidate("1","Dhvanananan","pending");
        candidateDao.insert(dhvanan);

        Candidate dhvananTrivedi = new Candidate("2","Dhvanan Trivedi","pending");
        candidateDao.insert(dhvananTrivedi);

        Candidate dhvananShah = new Candidate("3","Dhvanan Shah","pending");
        candidateDao.insert(dhvananShah);

        //exec
        List<Candidate> candidates = candidateDao.getByName("Dhvanan");
        System.out.println(candidates.size());
        //assertion
        for (Candidate candidate : candidates) {
            Assert.assertEquals(true,candidate.getName().contains("Dhvanan"));
        }
        Assert.assertNotNull(candidates);
        candidateDao.delete("1");
        candidateDao.delete("2");
        candidateDao.delete("3");
    }


}
