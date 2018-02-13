package com.brevitaz.RecruitmentManagementModule.dao.impl;

import com.brevitaz.RecruitmentManagementModule.dao.InterviewDao;
import com.brevitaz.RecruitmentManagementModule.model.Candidate;
import com.brevitaz.RecruitmentManagementModule.model.Interview;
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
 * @author dhvanan on 12/2/18 Monday
 * @project RecruitmentManagementModule
 **/
@Repository
public class InterviewDaoImpl implements InterviewDao {

    @Autowired
    RestHighLevelClient client;
    Environment environment;

    private ObjectMapper mapper = new ObjectMapper();

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CandidateDaoImpl.class);

    @Override
    //Insert the interview in the database
    public boolean insert(Interview interview){
        // init
        IndexRequest request = new IndexRequest(
                environment.getProperty("request.interviewIndex"),environment.getProperty("request.type"),interview.getCandidate().getCandidateId()
        );

        //exec
        try {

            String json = mapper.writeValueAsString(interview);
            request.source(json, XContentType.JSON);
            IndexResponse response = client.index(request);
            return (response.status()+"").equals("CREATED");

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //get Interview by candidate's Id


    @Override
    //get List<Interview> by candidate's name
    public List<Interview> getByName(String name){
        ///init
        List<Interview> interviews = new ArrayList<>();
        SearchRequest request = new SearchRequest(
                environment.getProperty("request.interviewIndex"),environment.getProperty("request.type")
        );
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("candidate.name", name)
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
                Interview interview = mapper.readValue(hit.getSourceAsString(), Interview.class);
                System.out.println(interview);
                interviews.add(interview);
            }
        }
        catch(IOException ioE){
            System.out.println(ioE);
        }

        return interviews;
    }

    @Override
    //update interview details
    public boolean update(String id,Interview interview){
        UpdateRequest request = new UpdateRequest(
                environment.getProperty("request.interviewIndex"),environment.getProperty("request.type"),id
        );

        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            String json = mapper.writeValueAsString(interview);
            request.doc(json,XContentType.JSON);
            UpdateResponse response = client.update(request);
            return (""+response.status()).equals("OK");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }





}
