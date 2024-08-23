package com.openwjk.central;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * @author wangjunkai
 * @description
 * @date 2024/8/23 10:20
 */
public class IntegerCompressToByte {
    @Test
    void test() {
        String s1 = "121071";
        String s2 = "131071";
        String s3 = "11283";
        String s4 = "2095";
        String s5 = "34";
        System.out.println(get2Bit(s1, +17) + "---" + get2Bit(s1, +17).length());
        System.out.println(get2Bit(s2, +17) + "---" + get2Bit(s2, +17).length());
        System.out.println(get2Bit(s3, +14) + "---" + get2Bit(s3, +14).length());
        System.out.println(get2Bit(s4, +12) + "---" + get2Bit(s4, +12).length());
        System.out.println(get2Bit(s5, +10) + "---" + get2Bit(s5, +10).length());
        String res = "00" + get2Bit(s1, +17) + get2Bit(s2, +17) + get2Bit(s3, +14) + get2Bit(s4, +12) + get2Bit(s5, +10);
        System.out.println(res + "---" + res.length());

        byte[] bts = new byte[9];
        for (int i = 0, j = 0; i < res.length(); i = i + 8, j++) {
            String to8Str = res.substring(i, i + 8);
            bts[j] = new Byte(to8(to8Str));
        }
        String res2Str = "";
        for (int x : bts) {
            res2Str += get2BitBySymbol(x);
        }
        res2Str=res2Str.substring(2);
        System.out.println(res2Str);
        System.out.println(to10(res2Str.substring(0, 17)));
        System.out.println(to10(res2Str.substring(17, 34)));
        System.out.println(to10(res2Str.substring(34, 48)));
        System.out.println(to10(res2Str.substring(48, 60)));
        System.out.println(to10(res2Str.substring(60, 70)));
    }

    private String to10(String to10Str) {
        char[] x = to10Str.toCharArray();

        BigDecimal res = BigDecimal.ZERO;
        for (int i = x.length - 1, j = 0; i >= 0; i--, j++) {
            if (String.valueOf(x[i]).equals("1")) {
                res = res.add(new BigDecimal("2").pow(j));
            }
        }
        return res.toString();
    }

    private String get2BitBySymbol(int x) {
        String res2 = "";
        if (x <= 0) {
            res2 = res2 + "0";
        } else {
            res2 = res2 + "1";
        }
        BigDecimal res2Big = new BigDecimal(x).abs();
        return res2 + get2Bit(res2Big.toString(), 7);
    }

    private String to8(String to8Str) {
        String first = to8Str.substring(0, 1);
        char[] x = to8Str.substring(1).toCharArray();

        BigDecimal res = BigDecimal.ZERO;
        for (int i = x.length - 1, j = 0; i >= 0; i--, j++) {
            if (String.valueOf(x[i]).equals("1")) {
                res = res.add(new BigDecimal("2").pow(j));
            }
        }
        return "1".equals(first) ? res.toString() : "-" + res;
    }

    String get2Bit(String s, Integer size) {
        BigDecimal x1 = new BigDecimal(s);
        Stack<String> stack = new Stack<>();
        while (!BigDecimal.ZERO.equals(x1)) {
            stack.push(x1.remainder(new BigDecimal("2")).toString());
            x1 = x1.divide(new BigDecimal("2"), 0, BigDecimal.ROUND_DOWN);
        }
        String result = "";
        for (int i = 0; i < size - stack.size(); i++) {
            result = result + "0";
        }
        while (!stack.isEmpty()) {
            result = result + stack.pop();
        }
        return result;
    }
}
