package com.urlshortnerapi.dbqueries;

import com.urlshortnerapi.model.Url;

public interface UrlCustomRepository {
    public Url findByLongUrlAndClientId(String longUrl, long clientId);
    public Url findByUrlId(long Urlid);
}
