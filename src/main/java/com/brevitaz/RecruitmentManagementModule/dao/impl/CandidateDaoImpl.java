package com.brevitaz.RecruitmentManagementModule.dao.impl;

import com.brevitaz.RecruitmentManagementModule.config.ClientConfig;
import com.brevitaz.RecruitmentManagementModule.dao.CandidateDao;
import com.brevitaz.RecruitmentManagementModule.model.Candidate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.IOException;
import org.apache.log4j.Logger;

/**
 * @author dhvanan on 8/2/18 Thursday
 * @project RecruitmentManagementModule
 **/
public class CandidateDaoImpl implements CandidateDao {

    @Autowired
    ClientConfig clientConfig;

    @Autowired
    Environment environment;

    @Autowired
    Logger logger;

    @Autowired
    ObjectMapper mapper;

    // Insert into database
    boolean register(String id, Candidate candidate){


        // init
        RestHighLevelClient client = clientConfig.getClient();
        String cId = id;
        IndexRequest request = new IndexRequest(
                environment.getProperty("request.index"),environment.getProperty("request.doc")
        );

        // execution
        try {

            String json = mapper.writeValueAsString(candidate);
            request.source(json, XContentType.JSON);
            IndexResponse response = client.index(request);
            return (response.status()+"").equals("OK");

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    void remove(){}

    void updateStatus(){
    }

    void updateData(){

    }

    void notifyCandidate(){

    }




}