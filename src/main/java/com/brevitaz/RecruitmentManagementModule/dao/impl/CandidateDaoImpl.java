package com.brevitaz.RecruitmentManagementModule.dao.impl;

import com.brevitaz.RecruitmentManagementModule.dao.CandidateDao;
import com.brevitaz.RecruitmentManagementModule.model.Candidate;
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
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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
    private RestHighLevelClient client;

    @Autowired
    private Environment environment;

    @Autowired
    private ObjectMapper mapper;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CandidateDaoImpl.class);

    private static final String TYPE = "doc";

    @Override
    public boolean insert(Candidate candidate){

        System.out.println("=========");

        IndexRequest request = new IndexRequest(
                environment.getProperty("request.candidateIndex"),TYPE,candidate.getId());

        LOGGER.info("Hello LOGGER");

        // execution
        try {

            String json = mapper.writeValueAsString(candidate);
            request.source(json, XContentType.JSON);
            IndexResponse response = client.index(request);
            LOGGER.info("status for candidate - insert"+response.status());
            return (response.status()+"").equals("CREATED");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
   public List<Candidate> getByName(String name){
        ///init
       List<Candidate> candidates = new ArrayList<>();
       SearchRequest request = new SearchRequest(
               environment.getProperty("request.candidateIndex"));
     //exec
       try {
           SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
           QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", name)
                   .fuzziness(Fuzziness.AUTO)
                   .prefixLength(3)
                   .maxExpansions(10);
           sourceBuilder.query(matchQueryBuilder);
           //sourceBuilder.query(QueryBuilders.boolQuery().must(matchQuery("name",name)));
           request.source(sourceBuilder);
           SearchResponse response = client.search(request);
           SearchHits hits = response.getHits();
           for (SearchHit hit : hits) {
               Candidate candidate = mapper.readValue(hit.getSourceAsString(), Candidate.class);
               LOGGER.info("Candidate : "+candidate);
               candidates.add(candidate);
           }
       }
       catch(IOException ioE){
               System.out.println(ioE);
       }
       return candidates;
   }

    public List<Candidate> getByKeyword(String name) {

        List<Candidate> candidates = new ArrayList<>();
        SearchRequest request = new SearchRequest(
                environment.getProperty("request.candidateIndex"));


        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("name",name));
 /*sourceBuilder.from(0);
 sourceBuilder.size(10);*/
        request.source(sourceBuilder);
        try {
            SearchResponse searchResponse = client.search(request);
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (SearchHit hit : hits) {
                Candidate candidate = mapper.readValue(hit.getSourceAsString(), Candidate.class);
                LOGGER.info("CANDIDATE : "+candidate);
                candidates.add(candidate);
            }
        }
        catch (Exception e ){
            e.printStackTrace();
        }
        return candidates;
    }


    @Override
    public boolean update( String id,Candidate candidate){
        UpdateRequest request = new UpdateRequest(
                environment.getProperty("request.candidateIndex"),TYPE,id
        );

        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            String json = mapper.writeValueAsString(candidate);
            request.doc(json,XContentType.JSON);
            UpdateResponse response = client.update(request);
            return (""+response.status()).equals("OK");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean delete(String id) {

        DeleteRequest deleteRequest = new DeleteRequest(
                environment.getProperty("request.candidateIndex"),TYPE, id);

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
                environment.getProperty("request.candidateIndex"),TYPE,id);

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
        searchRequest.types(TYPE);

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