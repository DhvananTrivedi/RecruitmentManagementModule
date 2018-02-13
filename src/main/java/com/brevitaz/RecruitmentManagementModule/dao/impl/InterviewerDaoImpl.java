package com.brevitaz.RecruitmentManagementModule.dao.impl;

import com.brevitaz.RecruitmentManagementModule.dao.InterviewerDao;
import com.brevitaz.RecruitmentManagementModule.model.Interviewer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dhvanan on 13/2/18 Tuesday
 * @project RecruitmentManagementModule
 **/
@Repository
public class InterviewerDaoImpl implements InterviewerDao {

    @Autowired
    RestHighLevelClient client;
    Environment environment;

    private ObjectMapper mapper = new ObjectMapper();

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CandidateDaoImpl.class);

    @Override
    //Insert the interviewer in the database
    public boolean insert(Interviewer interviewer) {
        // init
        IndexRequest request = new IndexRequest(
                environment.getProperty("request.interviewerIndex"), environment.getProperty("request.type"), interviewer.getId()
        );

        //exec
        try {

            String json = mapper.writeValueAsString(interviewer);
            request.source(json, XContentType.JSON);
            IndexResponse response = client.index(request);
            return (response.status() + "").equals("CREATED");

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    //get List<Interview> by candidate's name
    public List<Interviewer> getByName(String name) {
        ///init
        List<Interviewer> interviewers = new ArrayList<>();
        SearchRequest request = new SearchRequest(
                environment.getProperty("request.interviewerIndex"), environment.getProperty("request.type")
        );
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", name)
                .fuzziness(Fuzziness.AUTO)
                .prefixLength(3)
                .maxExpansions(10);

        //exec
        try {
            searchSourceBuilder.query(matchQueryBuilder);
            request.source(searchSourceBuilder);
            SearchResponse response = client.search(request);
            SearchHits hits = response.getHits();
            for (SearchHit hit : hits) {
                Interviewer interviewer = mapper.readValue(hit.getSourceAsString(), Interviewer.class);
                System.out.println(interviewer);
                interviewers.add(interviewer);
            }
        } catch (IOException ioE) {
            System.out.println(ioE);
        }

        return interviewers;
    }

    @Override
    //update interview details
    public boolean update(String id,Interviewer interviewer){
        UpdateRequest request = new UpdateRequest(
                environment.getProperty("request.interviewerIndex"),environment.getProperty("request.type"),id
        );

        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            String json = mapper.writeValueAsString(interviewer);
            request.doc(json,XContentType.JSON);
            UpdateResponse response = client.update(request);
            return (""+response.status()).equals("OK");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}
