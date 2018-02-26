package com.brevitaz.RecruitmentManagementModule.dao.impl;

import com.brevitaz.RecruitmentManagementModule.dao.InterviewDao;
import com.brevitaz.RecruitmentManagementModule.model.Interview;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
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
 * @author dhvanan on 12/2/18 Monday
 * @project RecruitmentManagementModule
 **/
@Repository
public class InterviewDaoImpl implements InterviewDao {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private Environment environment;

    @Autowired
    private ObjectMapper mapper;


    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CandidateDaoImpl.class);
    private static final String TYPE = "doc";

    @Override
    //Insert the interview in the database
    public boolean insert(Interview interview){


        IndexRequest request = new IndexRequest(
                environment.getProperty("request.interviewIndex"),TYPE,interview.getCandidate().getId());

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

    @Override
    public boolean delete(String id) {

        DeleteRequest deleteRequest = new DeleteRequest(
                environment.getProperty("request.interviewIndex"),TYPE, id);

        try {
            DeleteResponse response = client.delete(deleteRequest);
            return (response.status() + "").equals("OK");

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Interview getById(String id)
    {
        GetRequest request = new GetRequest(
                environment.getProperty("request.interviewIndex"),TYPE,id);

        try {
            GetResponse getResponse=client.get(request);
            Interview interview = mapper.readValue(getResponse.getSourceAsString(), Interview.class);
            return interview;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<Interview> getAll()
    {

        List<Interview> interviews = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest( environment.getProperty("request.interviewIndex"));
        searchRequest.types(TYPE);

        try {
            SearchResponse searchResponse = client.search(searchRequest);
            SearchHit[] hits = searchResponse.getHits().getHits();

            Interview interview;
            for (SearchHit hit : hits) {
                interview = mapper.readValue(hit.getSourceAsString(), Interview.class);
                interviews.add(interview);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return interviews;

    }

    @Override
    //update interview details
    public boolean update(String id,Interview interview){
        UpdateRequest request = new UpdateRequest(
                environment.getProperty("request.interviewIndex"),TYPE,id);

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

    @PostConstruct
    public void init() {
        LOGGER.error("logger integrated successfully");
    }


    //TODO: Not requred this method
 /*   @Override
    //get List<Interview> by candidate's name
    public List<Interview> getByName(String name){
        ///init
        List<Interview> interviews = new ArrayList<>();
        SearchRequest request = new SearchRequest(
                environment.getProperty("request.interviewIndex"));
        ///request.types(environment.getProperty("request.type"));
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
*/


}
