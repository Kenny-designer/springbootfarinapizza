package com.example.farinapizza.util;

public class GenerateVerificationCode {

    //產生三位數驗證碼
    public static short generateVerificationCode() { return (short)(( Math.random() * ( 999 - ( 100 - 1 )) ) + 100 ); }
}
