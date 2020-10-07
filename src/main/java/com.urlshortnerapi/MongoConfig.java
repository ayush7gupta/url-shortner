package com.urlshortnerapi;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "urlShortner";
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient("127.0.0.1", 27017);
    }

    @Bean
    MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }
}

