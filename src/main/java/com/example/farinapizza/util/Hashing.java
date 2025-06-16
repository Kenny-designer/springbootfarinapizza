package com.example.farinapizza.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Hashing {

    // 密碼加密
    public static String hashing(String password) { return (new BCryptPasswordEncoder()).encode(password); }
}
