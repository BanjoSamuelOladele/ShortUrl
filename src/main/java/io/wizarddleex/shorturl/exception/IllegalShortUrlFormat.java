package io.wizarddleex.shorturl.exception;

public class IllegalShortUrlFormat extends RuntimeException{
    public IllegalShortUrlFormat(String message){
        super(message);
    }
}
