package com.urlshortnerapi.controller;

import com.urlshortnerapi.model.Url;
import com.urlshortnerapi.model.UrlRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.urlshortnerapi.service.UrlService;
import util.AppendProtocol;


import java.net.URI;

@RestController
@RequestMapping("/api")
public class UrlController {

    private final UrlService urlService;
    private static final Logger log = LoggerFactory.getLogger(UrlController.class);

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }


    @PostMapping("create-short")
    public String convertToShortUrl(@RequestBody UrlRequest request) {
        log.info("recieved request with payload request {}", request.toString());
        return urlService.convertToShortUrl(request);
    }

    @GetMapping(value = "{shortUrl}")
    //@Cacheable(value = "urls", key = "#shortUrl", sync = true)
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        String url = urlService.getOriginalUrl(shortUrl);
        if(url == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }
        String modifiedUrl = AppendProtocol.appendProtocolToUrl(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(modifiedUrl));
        log.info("The long url found is {}", url);
        return new ResponseEntity<>(headers, HttpStatus.TEMPORARY_REDIRECT);
    }

    @GetMapping(value ="/stats/{shortUrl}")
    public ResponseEntity<Url> getStats(@PathVariable String shortUrl){
        Url url = urlService.getUrlObject(shortUrl);
        if(url == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

        return ResponseEntity.status(HttpStatus.FOUND)
                .body(url);
    }
}
