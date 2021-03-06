package com.brevitaz.RecruitmentManagementModule.dao.impl;

import com.brevitaz.RecruitmentManagementModule.config.ClientConfig;
import com.brevitaz.RecruitmentManagementModule.config.ObjectMapperProvider;
import com.brevitaz.RecruitmentManagementModule.dao.InterviewerDao;
import com.brevitaz.RecruitmentManagementModule.model.Interviewer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Repository
public class InterviewerDaoImpl implements InterviewerDao {

    @Autowired
    private ClientConfig client;

    @Autowired
    private Environment environment;

    @Autowired
    private ObjectMapperProvider mapper;


    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CandidateDaoImpl.class);
    private static final String TYPE = "doc";

    @Override
    //Insert the interviewer in the database
    public boolean insert(Interviewer interviewer) {
        // init
        IndexRequest request = new IndexRequest(
                environment.getProperty("elasticsearch.index.interviewer"),TYPE, interviewer.getId());
        mapper.getInstance().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //exec
        try {

            String json = mapper.getInstance().writeValueAsString(interviewer);
            request.source(json, XContentType.JSON);
            IndexResponse response = client.getClient().index(request);
            return (response.status()+"").equals("CREATED");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {

        DeleteRequest deleteRequest = new DeleteRequest(
                environment.getProperty("elasticsearch.index.interviewer"), TYPE, id);

        try {
            DeleteResponse response = client.getClient().delete(deleteRequest);
            return (response.status() + "").equals("OK");

       } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Interviewer getById(String id)
    {
        GetRequest request = new GetRequest(
                environment.getProperty("elasticsearch.index.interviewer"),TYPE,id);

        try {
            GetResponse getResponse=client.getClient().get(request);
            Interviewer interviewer = mapper.getInstance().readValue(getResponse.getSourceAsString(), Interviewer.class);
            return interviewer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Interviewer> getAll()
    {
        List<Interviewer> interviewers = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest( environment.getProperty("elasticsearch.index.interviewer"));
        searchRequest.types(TYPE);

        try {
            SearchResponse searchResponse = client.getClient().search(searchRequest);
            SearchHit[] hits = searchResponse.getHits().getHits();

            Interviewer interviewer;
            for (SearchHit hit : hits) {
                interviewer = mapper.getInstance().readValue(hit.getSourceAsString(), Interviewer.class);
                interviewers.add(interviewer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return interviewers;

    }


    @PostConstruct
    public void init() {
        LOGGER.error("logger integrated successfully");
    }


    //get List<Interview> by name
    public List<Interviewer> getByName(String name) {

        List<Interviewer> interviewers = new ArrayList<>();
        SearchRequest request = new SearchRequest(
                environment.getProperty("elasticsearch.index.interviewer"));


        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", name)
                    .fuzziness(Fuzziness.AUTO)
                    .prefixLength(3)
                    .maxExpansions(10);
            sourceBuilder.query(matchQueryBuilder);
            //sourceBuilder.query(QueryBuilders.boolQuery().must(matchQuery("name",name)));
            request.source(sourceBuilder);
            SearchResponse response = client.getClient().search(request);
            SearchHits hits = response.getHits();
            for (SearchHit hit : hits) {
                Interviewer interviewer = mapper.getInstance().readValue(hit.getSourceAsString(), Interviewer.class);
                LOGGER.info("Interviewer  : "+interviewer);
                interviewers.add(interviewer);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return interviewers;

    }

    //update interviewer details
    @Override
    public boolean update(String id,Interviewer interviewer){
        UpdateRequest request = new UpdateRequest(
                environment.getProperty("elasticsearch.index.interviewer"),TYPE,id);

        mapper.getInstance().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            String json = mapper.getInstance().writeValueAsString(interviewer);
            request.doc(json,XContentType.JSON);
            UpdateResponse response = client.getClient().update(request);
            return (""+response.status()).equals("OK");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //getByCandidateID
    public List<Interviewer> getByCandidateId(String candidateId)
    {
        List<Interviewer> interviewers = new ArrayList<>();
        SearchRequest request = new SearchRequest(
                environment.getProperty("elasticsearch.index.interviewer"));

        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("candidateId", candidateId)
                    .fuzziness(Fuzziness.AUTO)
                    .prefixLength(3)
                    .maxExpansions(10);
            sourceBuilder.query(matchQueryBuilder);
            request.source(sourceBuilder);
            SearchResponse response = client.getClient().search(request);
            SearchHits hits = response.getHits();
            for (SearchHit hit : hits) {
                Interviewer interviewer = mapper.getInstance().readValue(hit.getSourceAsString(), Interviewer.class);
                LOGGER.info("Interviwer  : "+interviewer);
                interviewers.add(interviewer);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return interviewers;
    }
}
