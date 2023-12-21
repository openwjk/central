package com.openwjk.central;

import com.openwjk.commons.utils.HttpClientUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjunkai
 * @description
 * @date 2023/12/13 17:02
 */
public class DateTest {

    @Test
    void getDate() {
        Map<String, String> map = new HashMap<>();
        map.put("wd","今天");
        String resp = HttpClientUtil.httpGet("https://www.baidu.com/s", null, map);
        System.out.println(1);
    }
}
