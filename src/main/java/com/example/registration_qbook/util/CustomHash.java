package com.example.registration_qbook.util;


import org.apache.commons.codec.digest.DigestUtils;

public interface CustomHash {
    static String hashString(String s){
        return DigestUtils.sha256Hex(s);
    }
}
