package com.example.iot.utils;

import java.math.BigDecimal;

public class RamdomUtils {

    public static BigDecimal ramdomValue(){
        return rangBigDecimal(42,46);
    }

    private static BigDecimal rangBigDecimal(int min, int max) {
        int value = (int) (Math.random()*(max-min)+min);
        return new BigDecimal(value);
    }
}
