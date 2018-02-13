package com.brevitaz.RecruitmentManagementModule.dao.impl;

import com.brevitaz.RecruitmentManagementModule.config.ClientConfig;
import com.brevitaz.RecruitmentManagementModule.dao.CandidateDao;
import com.brevitaz.RecruitmentManagementModule.model.Candidate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author dhvanan on 8/2/18 Thursday
 * @project RecruitmentManagementModule
 **/

@Repository
public class CandidateDaoImpl implements CandidateDao {

    @Autowired
    RestHighLevelClient client;
    Environment environment;

    private ObjectMapper mapper = new ObjectMapper();

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CandidateDaoImpl.class);
    // Insert into database

    @Override
    public boolean insert(Candidate candidate){


        // init
        IndexRequest request = new IndexRequest(
                environment.getProperty("request.candidateIndex"),environment.getProperty("request.doc")
        );

        // execution
        try {

            String json = mapper.writeValueAsString(candidate);
            request.source(json, XContentType.JSON);
            IndexResponse response = client.index(request);
            return (response.status()+"").equals("CREATED");

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean delete(String id) {

        DeleteRequest deleteRequest = new DeleteRequest(
                environment.getProperty("request.candidateIndex"), environment.getProperty("request.doc"), id);

        try {
            DeleteResponse response = client.delete(deleteRequest);
            return (response.status() + "").equals("OK");

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Candidate getById(String id)
    {
        GetRequest request = new GetRequest(
                environment.getProperty("request.candidateIndex"),environment.getProperty("request.doc"),id
        );

        try {
            GetResponse getResponse=client.get(request);
            Candidate candidate = mapper.readValue(getResponse.getSourceAsString(), Candidate.class);
            return candidate;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Candidate> getAll()
    {

        List<Candidate> candidates = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest( environment.getProperty("request.candidateIndex"));
        searchRequest.types(environment.getProperty("request.doc"));

        try {
            SearchResponse searchResponse = client.search(searchRequest);
            SearchHit[] hits = searchResponse.getHits().getHits();

            Candidate candidate;

            for (SearchHit hit : hits) {
                candidate = mapper.readValue(hit.getSourceAsString(), Candidate.class);
                candidates.add(candidate);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return candidates;

    }


    @PostConstruct
    public void init() {
        LOGGER.error("logger integrated successfully");
    }

}