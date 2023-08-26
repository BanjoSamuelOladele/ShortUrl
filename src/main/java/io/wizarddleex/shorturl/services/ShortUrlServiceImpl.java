package io.wizarddleex.shorturl.services;

import io.wizarddleex.shorturl.data.model.ShortUrl;
import io.wizarddleex.shorturl.data.repositories.ShortUrlRepository;
import io.wizarddleex.shorturl.mapper.Mapper;
import io.wizarddleex.shorturl.utils.CheckForTheHttp;
import io.wizarddleex.shorturl.utils.UrlGenerator;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static io.wizarddleex.shorturl.mapper.Mapper.map;

@Service
public class ShortUrlServiceImpl implements ShortUrlService{
    private final ShortUrlRepository shortUrlRepository;
    public ShortUrlServiceImpl(ShortUrlRepository shortUrlRepository){
        this.shortUrlRepository = shortUrlRepository;
    }
    @Override
    public ShortUrl shortenLongUrl(String longUrl) {
        CheckForTheHttp.checkIfLongUrlIfItStartsWithHttp(longUrl);
        return shortenUrl(longUrl);
    }
    private ShortUrl shortenUrl(String longUrl){
        boolean check = shortUrlRepository.existsShortUrlByLongUrl(longUrl);
        if (check) return shortUrlRepository.findShortUrlByLongUrl(longUrl).get();
        else return createShortUrl(longUrl);
    }
    private ShortUrl createShortUrl(String longUrl){
        ShortUrl shortUrl = checkSomething(longUrl);
        return shortUrlRepository.save(shortUrl);
    }
    private ShortUrl checkSomething(String longUrl){
        String shortGeneratedUrl = generateUrl();
        return map(longUrl, shortGeneratedUrl);
    }
    @Override
    public ShortUrl correspondingLongUrl(String shortUrl) {
        Optional<ShortUrl> foundUrl = shortUrlRepository.findShortUrlByShortUrl(shortUrl);
        return Mapper.map(foundUrl);
    }
    private String generateUrl(){
        String generatedUrl = UrlGenerator.generateUrl();
        boolean check = shortUrlRepository.existsShortUrlByShortUrl(generatedUrl);
        if (check){
            generateUrl();
        }
        return generatedUrl;
    }
}
