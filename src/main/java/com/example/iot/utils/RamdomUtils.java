package com.example.iot.utils;

import java.math.BigDecimal;

public class RamdomUtils {

    public static BigDecimal ramdomValue(){
        return rangBigDecimal(8,12);
    }

    private static BigDecimal rangBigDecimal(int min, int max) {
        int value = (int) (Math.random()*(max-min)+min);
        return new BigDecimal(value);
    }
}
