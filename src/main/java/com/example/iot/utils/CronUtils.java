package com.example.iot.utils;

public class CronUtils {

    public static final String  template = "*/%d * * * * ?";

    public static String radomCron(){
        return String.format(template,RamdomUtils.ramdomValue().intValue());
    }

    public static void main(String[] args) {
        System.out.println(radomCron());
    }
}
