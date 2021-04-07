package com.barry.baselib.utils;

import android.text.TextUtils;

import java.math.BigDecimal;

/**
 * ================================================
 * @description 数字操作工具类
 * @author barry
 * create at 4/6/21 11:37 AM
 * ================================================
 */
public class NumberUtils {

    /**
     * @param number   Object
     * @param h        保留几位小数
     * @param model    是否四射侮辱
     * @param saveZero 是否保留小数点后多余的0
     * @return String
     */
    public static String convert45(Object number, int h, boolean model, boolean saveZero) {
        BigDecimal bd;
        if (number instanceof String) {
            if (TextUtils.isEmpty((String) number) || ((String) number).equals("0")) {
                return "0";
            } else {
                bd = BigDecimal.valueOf(Double.parseDouble((String) number));
            }
        } else if (number instanceof Double) {
            bd = BigDecimal.valueOf((Double) number);
        } else if (number instanceof Long) {
            bd = BigDecimal.valueOf((Long) number);
        } else if (number instanceof Integer) {
            bd = BigDecimal.valueOf((Integer) number);
        } else if (number instanceof BigDecimal) {
            bd = (BigDecimal) number;
        } else {
            return "0";
        }
        String r;
        if (model) {
            if (saveZero) r = bd.setScale(h, BigDecimal.ROUND_HALF_UP).toPlainString();
            else r = bd.setScale(h, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
        } else {
            if (saveZero) r = bd.setScale(h, BigDecimal.ROUND_DOWN).toPlainString();
            else r = bd.setScale(h, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        }
        if (Double.parseDouble(r) == 0) return "0";

        return r;
    }

    /**
     * @param number1
     * @param number2
     * @param h
     * @param model   是否四射侮辱
     * @return string
     */
    public static String convert45multiply(double number1, double number2, int h, boolean model, boolean saveZero) {
        BigDecimal bd = BigDecimal.valueOf(number1).multiply(BigDecimal.valueOf(number2));
        return convert45(bd.doubleValue(), h, model, saveZero);
    }

    public static String convert45Divide(double number1, double number2, int h, boolean model, boolean saveZero) {
        BigDecimal bd = BigDecimal.valueOf(number1).divide(BigDecimal.valueOf(number2), h, BigDecimal.ROUND_HALF_UP);
        return convert45(bd.doubleValue(), h, model, saveZero);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double multiply(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }
}
