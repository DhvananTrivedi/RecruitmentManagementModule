package dao;

import com.brevitaz.RecruitmentManagementModule.RecruitmentManagementModuleApplication;
import com.brevitaz.RecruitmentManagementModule.TestA;
import com.brevitaz.RecruitmentManagementModule.dao.CandidateDao;
import com.brevitaz.RecruitmentManagementModule.dao.impl.CandidateDaoImpl;
import com.brevitaz.RecruitmentManagementModule.model.Candidate;
import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RecruitmentManagementModuleApplication.class)
@WebAppConfiguration
public class CandidateDaoImplTest {


    @Test
    public void test() {
        System.out.println("Hello TEST");
    }


    @Autowired
    CandidateDao candidateDao;


    @Test
    public void testInsert() {

        System.out.println("TEST INSERT");
        Candidate candidate = new Candidate();
        candidate.setId("1");
        candidate.setName("Mahima");
        System.out.println(candidate);
        boolean status = candidateDao.insert(candidate);
        System.out.println(status);
        Assert.assertEquals(true, status);
    }

    @Test
    public void testDelete() {
        boolean status = candidateDao.delete("121212");
        Assert.assertEquals(true, status);
    }

    @Test
    public void testUpdate() {
        Candidate candidate = new Candidate();
        candidate.setName("aaaa");
        boolean status = candidateDao.update("121414", candidate);
        Assert.assertEquals(true, status);
    }

    @Test
    public void testGetAll() {
        List<Candidate> candidates = candidateDao.getAll();
        Assert.assertNotNull(candidates);
    }

    @Test
    public void testGetById() {
        Candidate candidate = candidateDao.getById("121414");
        Assert.assertNotNull(candidate);
    }

    @Test
    public void testGetByKeyword() {

    }

    @Test
    public void testGetByName() {
        List<Candidate> candidates = candidateDao.getByName("aaaa");
        Assert.assertNotNull(candidates);
    }

}