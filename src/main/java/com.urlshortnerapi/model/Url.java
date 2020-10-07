package com.urlshortnerapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "url")
public class Url {
    String longUrl;
    long clientId;
    @Id
    private String id;
    private int urlId;
    private int stats = 0;

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStats() {
        return stats;
    }

    public void setStats(int stats) {
        this.stats = stats;
    }

    public int getUrlId() {
        return urlId;
    }

    public void setUrlId(int urlId) {
        this.urlId = urlId;
    }
}
