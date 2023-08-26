package io.wizarddleex.shorturl.utils;

import java.security.SecureRandom;

public class UrlGenerator {
    public static String generateUrl(){
        StringBuilder newString = new StringBuilder("diw.io/");
        final String ALL_CONSTANT = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        char[] newCharacters = new char[7];
        for (int index = 0; index < newCharacters.length; index++) {
            newCharacters[index] = ALL_CONSTANT.charAt(random.nextInt(0, 62));
        }
        return newString.append(newCharacters).toString();
    }
}
