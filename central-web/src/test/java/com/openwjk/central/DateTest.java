package com.openwjk.central;

import com.openwjk.commons.utils.HttpClientUtil;
import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author wangjunkai
 * @description
 * @date 2023/12/13 17:02
 */
public class DateTest {
    String[] arr = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
    String[] arr2 = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};

    @Test
    void test() {
        String s1 = "131071";
        String s2 = "131071";
        String s3 = "16383";
        String s4 = "4095";
        String s5 = "511";
        System.out.println(get2Bit(s1, +17) + "---" + get2Bit(s1, +17).length());
        System.out.println(get2Bit(s2, +17) + "---" + get2Bit(s2, +17).length());
        System.out.println(get2Bit(s3, +14) + "---" + get2Bit(s3, +14).length());
        System.out.println(get2Bit(s4, +12) + "---" + get2Bit(s4, +12).length());
        System.out.println(get2Bit(s5, +10) + "---" + get2Bit(s5, +10).length());
        String res = "00" + get2Bit(s1, +17) + get2Bit(s2, +17) + get2Bit(s3, +14) + get2Bit(s4, +12) + get2Bit(s5, +10);
        System.out.println(res + "---" + res.length());

        String res16 = "";
        for (int i = 0; i < res.length(); i = i + 8) {
            String to16Str = res.substring(i, i + 8);
            res16 = res16 + to16(to16Str);
        }
        System.out.println(res16);
    }

    private String to16(String to16Str) {
        char[] x = to16Str.toCharArray();

        BigDecimal res = BigDecimal.ZERO;
        for (int i = x.length - 1, j = 0; i >= 0; i--, j++) {
            if (String.valueOf(x[i]).equals("1")) {
                res = res.add(new BigDecimal("2").pow(j));
            }
        }
        return res.toString();
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

    @Test
    void getDate() {
        Map<String, String> map = new HashMap<>();
        map.put("wd", "今天");
        String resp = HttpClientUtil.httpGet("https://www.baidu.com/s", null, map);
        System.out.println(1);
    }
}
