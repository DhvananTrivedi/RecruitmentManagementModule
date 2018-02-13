package com.brevitaz.RecruitmentManagementModule.dao.impl;

import com.brevitaz.RecruitmentManagementModule.dao.CandidateDao;
import com.brevitaz.RecruitmentManagementModule.model.Candidate;
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

    @Autowired
    Environment environment;

    private ObjectMapper mapper = new ObjectMapper();

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CandidateDaoImpl.class);

    @Override
    // Insert into database
    public boolean insert( Candidate candidate){


        // init
        IndexRequest request = new IndexRequest(
                environment.getProperty("request.candidateIndex"),environment.getProperty("request.type"),candidate.getCandidateId()
        );


        // execution
        try {

            String json = mapper.writeValueAsString(candidate);
            request.source(json, XContentType.JSON);
            IndexResponse response = client.index(request);
            return (response.status()+"").equals("CREATED");

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    void remove(String id){

    }

    @Override
   public List<Candidate> getByName(String name){
        ///init
       List<Candidate> candidates = new ArrayList<>();
       SearchRequest request = new SearchRequest(
               environment.getProperty("request.candidateIndex"),environment.getProperty("request.type")
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
               Candidate candidate = mapper.readValue(hit.getSourceAsString(), Candidate.class);
               System.out.println(candidate);
               candidates.add(candidate);
           }
       }
       catch(IOException ioE){
               System.out.println(ioE);
       }
       return candidates;
   }


   @Override
    public boolean update( String id,Candidate candidate){
        UpdateRequest request = new UpdateRequest(
                environment.getProperty("request.candidateIndex"),environment.getProperty("request.type"),id
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


    @PostConstruct
    public void init() {
        LOGGER.error("logger integrated successfully");
    }

}