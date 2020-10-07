package com.urlshortnerapi.dbqueries.impl;

import com.urlshortnerapi.dbqueries.UrlCustomRepository;
import com.urlshortnerapi.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class UrlCustomRepositoryImpl implements UrlCustomRepository {

    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public Url findByLongUrlAndClientId(String longUrl, long clientId){
        Criteria criteria = Criteria
                .where("longUrl").is(longUrl)
                .and("clientId").is(clientId);

        Query query = new Query(criteria);
        return mongoTemplate.findOne(query, Url.class);
    }

    @Override
    public Url findByUrlId(long id){
        Criteria criteria = Criteria
                .where("urlId").is(id);

        Query query = new Query(criteria);
        return mongoTemplate.findOne(query, Url.class);
    }
}
