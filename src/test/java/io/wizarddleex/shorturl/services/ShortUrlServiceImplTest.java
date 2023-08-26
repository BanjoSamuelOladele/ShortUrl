package io.wizarddleex.shorturl.services;

import io.wizarddleex.shorturl.ShortUrlApplication;
import io.wizarddleex.shorturl.data.model.ShortUrl;
import io.wizarddleex.shorturl.exception.IllegalLongUrlFormat;
import io.wizarddleex.shorturl.exception.IllegalShortUrlFormat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ContextConfiguration(classes = {ShortUrlApplication.class})
public class ShortUrlServiceImplTest {
    private final ShortUrlServiceImpl shortUrlService;
    @Autowired
    public ShortUrlServiceImplTest(ShortUrlServiceImpl urlService){
        this.shortUrlService = urlService;
    }
    @Test
    public void testThatItReturnsSomething(){
        String longUrl = "https://www.google.com/search?q=what+is+radio+wave&sca_esv=559545509&sxsrf=AB5stBjiRg8Lj52ZYzexZ3qO_oD3Iw-xcg%3A1692832845354&ei=TZTmZIikFZOChbIP7bGTsA0&ved=0ahUKEwjI_qTj9fOAAxUTQUEAHe3YBNYQ4dUDCA4&uact=5&oq=what+is+radio+wave&gs_lp=Egxnd3Mtd2l6LXNlcnAiEndoYXQgaXMgcmFkaW8gd2F2ZTIIEAAYgAQYywEyCBAAGIAEGMsBMggQABiABBjLATIIEAAYgAQYywEyCBAAGIAEGMsBMggQABiABBjLATIIEAAYgAQYywEyCBAAGIAEGMsBMggQABiABBjLATIIEAAYgAQYywFIwxlQ0gNY-xdwAXgBkAEAmAGSAqAB-Q-qAQMyLTm4AQPIAQD4AQHCAgoQABhHGNYEGLADwgIEECMYJ8ICChAAGIAEGAoYywHiAwQYACBBiAYBkAYI&sclient=gws-wiz-serp";
        ShortUrl shortUrl = shortUrlService.shortenLongUrl(longUrl);
        String expected = "diw.io";
        assertEquals(expected, shortUrl.getShortUrl().substring(0,6));
    }
    @Test
    public void testThatTheTotalLengthOfTheShortUrlIs14(){
        String longUrl = "https://stackoverflow.com/questions/26889970/intellij-incorrectly-saying-no-beans-of-type-found-for-autowired-repository";
        ShortUrl shortUrl = shortUrlService.shortenLongUrl(longUrl);
        assertEquals(14, shortUrl.getShortUrl().length());
    }
    @Test
    public void testThatLongUrlWithNoHttpsOrHttpThrowsAnError(){
        String longUrl = "stackoverflow.com/questions/26889970/intellij-incorrectly-saying-no-beans-of-type-found-for-autowired-repository";
        assertThrows(IllegalLongUrlFormat.class, ()-> shortUrlService.shortenLongUrl(longUrl));
        try {
            shortUrlService.shortenLongUrl(longUrl);
        }catch (Exception exception){
            assertEquals(IllegalLongUrlFormat.class, exception.getClass());
            assertEquals("link format error", exception.getMessage());
        }
    }
    @Test
    public void testThatWhenAnExistingLongUrlIsEnteredTheCorrespondingShortUrlIsReturned(){
        String longUrl = "https://stackoverflow.com/questions/26889970/intellij-incorrectly-saying-no-beans-of-type-found-for-autowired-repository";
        ShortUrl result = shortUrlService.shortenLongUrl(longUrl);
        assertEquals("diw.io/qt2mnzW", result.getShortUrl());
    }
    @Test
    public void testThatShortUrlWouldReturnTheCorrespondingLongUrl(){
        String longUrl = "https://stackoverflow.com/questions/26889970/intellij-incorrectly-saying-no-beans-of-type-found-for-autowired-repository";
        String shortUrl = "diw.io/qt2mnzW";
        ShortUrl result = shortUrlService.correspondingLongUrl(shortUrl);
        assertEquals(longUrl, result.getLongUrl());
    }
    @Test
    public void testThatShortUrlWouldReturnTheCorrespondingLongUrlAgain(){
        String longUrl = "https://www.google.com/search?q=what+is+radio+wave&sca_esv=559545509&sxsrf=AB5stBjiRg8Lj52ZYzexZ3qO_oD3Iw-xcg%3A1692832845354&ei=TZTmZIikFZOChbIP7bGTsA0&ved=0ahUKEwjI_qTj9fOAAxUTQUEAHe3YBNYQ4dUDCA4&uact=5&oq=what+is+radio+wave&gs_lp=Egxnd3Mtd2l6LXNlcnAiEndoYXQgaXMgcmFkaW8gd2F2ZTIIEAAYgAQYywEyCBAAGIAEGMsBMggQABiABBjLATIIEAAYgAQYywEyCBAAGIAEGMsBMggQABiABBjLATIIEAAYgAQYywEyCBAAGIAEGMsBMggQABiABBjLATIIEAAYgAQYywFIwxlQ0gNY-xdwAXgBkAEAmAGSAqAB-Q-qAQMyLTm4AQPIAQD4AQHCAgoQABhHGNYEGLADwgIEECMYJ8ICChAAGIAEGAoYywHiAwQYACBBiAYBkAYI&sclient=gws-wiz-serp";
        String shortUrl = "diw.io/5VCQoAy";
        ShortUrl result = shortUrlService.correspondingLongUrl(shortUrl);
        assertEquals(longUrl, result.getLongUrl());
    }
    @Test
    public void shortUrlThatDoesNotExistCannotReturnAnyLongUrl(){
        String shortUrl = "diw.io/1111111";
        assertThrows(IllegalShortUrlFormat.class, ()-> shortUrlService.correspondingLongUrl(shortUrl));
        try{
            shortUrlService.correspondingLongUrl(shortUrl);
        }catch (Exception exception){
            assertEquals(IllegalShortUrlFormat.class, exception.getClass());
            assertEquals("No corresponding link found", exception.getMessage());
        }
    }
}