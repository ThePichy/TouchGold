package com.pichy.utils;

public class Chat {
    public static String translate(String str){
            return str.replaceAll("&", "\u00A7");
        }
}
