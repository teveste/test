package com.pelmorex.irprototype.util;

/**
 * Created by hbenzamane on 1/12/2016.
 */
public class MathUtil {
    public static final double PI_DOUBLE = Math.PI;
    public static final double TWO_PI_DOUBLE = 2 * PI_DOUBLE;
    public static final double PI_OVER_TWO_DOUBLE = PI_DOUBLE / 2;
    public static final double PI_OVER_FOUR_DOUBLE = PI_DOUBLE / 4;
    public static final float PI_FLOAT = (float) PI_DOUBLE;
    public static final float TWO_PI_FLOAT = (float) TWO_PI_DOUBLE;
    public static final float PI_OVER_TWO_FLOAT = (float) PI_OVER_TWO_DOUBLE;
    public static final float PI_OVER_FOUR_FLOAT = (float) PI_OVER_FOUR_DOUBLE;

    private MathUtil() {  }

    public static int[] getBase10Digits(int number) {
        int n = Math.abs(number);
        int size = (int) Math.ceil(Math.log10(n));
        int[] digits = new int[size];
        for ( int i = 0; i < size; ++i ) { digits[i] = (int) ( n / ( Math.pow(10, size - 1 - i) ) % 10 ); }
        return digits;
    }
}
