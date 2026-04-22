package com.zyh.shortlink.admin.toolkit;

import java.security.SecureRandom;

/**
 * author 邹宇航  @vision 1.0
 */
public class RandomGenerator {

    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandom(){
        return generateRandom(6);
    }

    public static String generateRandom(int length){
        StringBuilder stringBuilder = new StringBuilder(length);
        for(int i=0;i<length;i++){
            int randomIndex=RANDOM.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }
}
