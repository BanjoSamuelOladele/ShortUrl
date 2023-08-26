package io.wizarddleex.shorturl.utils;

import io.wizarddleex.shorturl.exception.IllegalLongUrlFormat;

public class CheckForTheHttp {
    public static boolean checkIfLongUrlIfItStartsWithHttp(String longUrl) {
        if (longUrl.substring(0, 7).equals("http://") || longUrl.substring(0, 8).equals("https://")) return true;
        else throw new IllegalLongUrlFormat("link format error");
    }
}
