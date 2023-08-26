package io.wizarddleex.shorturl.mapper;

import io.wizarddleex.shorturl.data.model.ShortUrl;
import io.wizarddleex.shorturl.exception.IllegalShortUrlFormat;

import java.util.Optional;

public class Mapper {
    public static ShortUrl map(String longUrl, String shortGeneratedUrl) {
        ShortUrl shortUrl = ShortUrl.builder().build();
        shortUrl.setLongUrl(longUrl);
        shortUrl.setShortUrl(shortGeneratedUrl);
        return shortUrl;
    }
    public static ShortUrl map(Optional<ShortUrl> foundUrl) {
        if (foundUrl.isEmpty()) throw new IllegalShortUrlFormat("No corresponding link found");
        return foundUrl.get();
    }
}
