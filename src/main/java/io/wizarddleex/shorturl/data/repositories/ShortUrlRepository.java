package io.wizarddleex.shorturl.data.repositories;

import io.wizarddleex.shorturl.data.model.ShortUrl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ShortUrlRepository extends MongoRepository<ShortUrl, String> {

    boolean existsShortUrlByShortUrl(String longUrl);
    Optional<ShortUrl> findShortUrlByLongUrl(String longUrl);
    boolean existsShortUrlByLongUrl(String longUrl);

    Optional<ShortUrl> findShortUrlByShortUrl(String shortUrl);
}
