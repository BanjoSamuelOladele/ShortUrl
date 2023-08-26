package io.wizarddleex.shorturl;

import io.wizarddleex.shorturl.data.model.ShortUrl;
import io.wizarddleex.shorturl.data.repositories.ShortUrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ShortUrlApplicationTests {
	private final ShortUrlRepository repository;
	@Autowired
	public ShortUrlApplicationTests(ShortUrlRepository shortUrlRepository){
		this.repository = shortUrlRepository;
	}
	@BeforeEach void startsWith(){
		repository.deleteAll();
	}
	@Test
	void contextLoads() {
		ShortUrl shortUrl = new ShortUrl();
		shortUrl.setShortUrl("https://wizard-dleex.io/we43get");
		shortUrl.setLongUrl("https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api/5.10.0");
		ShortUrl newShort = repository.save(shortUrl);
		assertNotNull(newShort);
	}
}
