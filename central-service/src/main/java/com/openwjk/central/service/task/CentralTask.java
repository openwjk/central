package com.openwjk.central.service.task;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.openwjk.central.dao.mapper.CtConfigDOMapper;
import com.openwjk.central.dao.model.CtConfigDO;
import com.openwjk.central.dao.model.CtConfigDOExample;
import com.openwjk.central.service.utils.ChineseCalendar;
import com.openwjk.central.service.utils.HttpClientUtil;
import com.openwjk.commons.utils.Constant;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CentralTask {
    @Autowired
    CtConfigDOMapper configDOMapper;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void runTask() {

    }

    @Scheduled(cron = "0 0 9 * * ?")
    public void birthdayReminder() {
        ChineseCalendar calendar = ChineseCalendar.as(new Date());
        String birthDay = calendar.getChinaString().substring(calendar.getChinaString().indexOf("年") + 1);
        CtConfigDOExample example = new CtConfigDOExample();
        example.createCriteria()
                .andGroupCodeEqualTo("BIRTHDAY_REMINDER")
                .andCodeEqualTo(birthDay)
                .andIsDeletedEqualTo(Constant.STR_N);
        List<CtConfigDO> configDOList = configDOMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(configDOList)) return;
        CtConfigDO configDO = configDOList.get(Constant.INT_ZERO);
        List<String> name = JSONArray.parseArray(configDO.getValue(), String.class);
        String names = name.stream().collect(Collectors.joining(","));
        String desc = "今天农历" + birthDay + "是" + names + "生日，记得送上生日祝福哦~";
        Map<String, Object> map = new HashMap<>();
        Map<String, String> textMap = new HashMap<>();
        map.put("msgtype", "text");
        map.put("text", textMap);
        textMap.put("content", desc);
        HttpClientUtil.httpPost("url", JSON.toJSONString(map), StandardCharsets.UTF_8.name());
    }
}
