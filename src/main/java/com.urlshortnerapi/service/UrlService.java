package com.urlshortnerapi.service;

import com.urlshortnerapi.dbqueries.UrlRepository;
import com.urlshortnerapi.model.Url;
import com.urlshortnerapi.model.UrlRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    @Autowired
    BaseConversion baseConversion;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UrlRepository urlRepository;

    @Autowired
    NextSequenceService nextSequenceService;



    public String convertToShortUrl(UrlRequest request) {

        Url oldUrl = urlRepository.findByLongUrlAndClientId(request.getLongUrl(), request.getClientId());
        if(oldUrl != null){
            return baseConversion.encode(oldUrl.getUrlId());
        }
        Url url = new Url();
        url.setLongUrl(request.getLongUrl());
        url.setClientId(request.getClientId());
        url.setUrlId(nextSequenceService.getNextSequence("customSequences"));
        url.setStats(0);

        mongoTemplate.save(url, "url");
        return baseConversion.encode(url.getUrlId());
    }

    public String getOriginalUrl(String shortUrl) {
        long urlId = baseConversion.decode(shortUrl);
        Url url = urlRepository.findByUrlId(urlId);

        if(url == null)
            return null;

        url.setStats(url.getStats()+1);
        mongoTemplate.save(url, "url");

        return url.getLongUrl();
    }

    public Url getUrlObject(String shortUrl){
        long urlId = baseConversion.decode(shortUrl);
        Url url = urlRepository.findByUrlId(urlId);

        if(url == null)
            return null;
        return url;
    }
}
