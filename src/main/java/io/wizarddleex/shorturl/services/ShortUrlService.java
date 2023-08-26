package io.wizarddleex.shorturl.services;

import io.wizarddleex.shorturl.data.model.ShortUrl;


public interface ShortUrlService {
    ShortUrl shortenLongUrl(String longUrl);

    ShortUrl correspondingLongUrl(String shortUrl);
}
