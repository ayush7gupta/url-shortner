package com.urlshortnerapi.dbqueries;

import com.urlshortnerapi.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<Url, String>, UrlCustomRepository {
}
