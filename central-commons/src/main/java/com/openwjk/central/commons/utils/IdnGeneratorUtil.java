package com.openwjk.central.commons.utils;

import com.google.common.collect.Lists;
import com.openwjk.commons.utils.Constant;
import com.openwjk.commons.utils.RandomCodeUtil;

import java.util.List;

/**
 * @author wangjunkai
 * @description
 * @date 2023/9/11 20:24
 */
public class IdnGeneratorUtil {
    private static final List<Integer> weight = Lists.newArrayList(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
    private static final List<String> verify = Lists.newArrayList("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");

    public static String IdnGenerator(String area, String birth) {
        String random = RandomCodeUtil.generateNum(3);
        String idn17 = area + birth + random;
        int sum = 0;
        for (int i = 0; i < idn17.length(); i++) {
            int num = Integer.parseInt(String.valueOf(idn17.charAt(i)));
            sum += num * weight.get(i);
        }
        int remainder = sum % 11;
        return area + birth + random + verify.get(remainder);
    }

}
