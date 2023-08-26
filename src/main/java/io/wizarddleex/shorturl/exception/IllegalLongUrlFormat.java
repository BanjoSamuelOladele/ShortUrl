package io.wizarddleex.shorturl.exception;

public class IllegalLongUrlFormat extends RuntimeException{
    public IllegalLongUrlFormat(String message){
        super(message);
    }
}
