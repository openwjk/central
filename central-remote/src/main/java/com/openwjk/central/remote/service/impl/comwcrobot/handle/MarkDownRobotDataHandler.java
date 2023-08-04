package com.openwjk.central.remote.service.impl.comwcrobot.handle;

import com.alibaba.fastjson2.JSON;
import com.openwjk.central.commons.domain.CacheableResultDTO;
import com.openwjk.central.commons.enums.CtConfigGroupEnum;
import com.openwjk.central.dao.model.CtConfigDO;
import com.openwjk.central.remote.dto.Context;
import com.openwjk.central.remote.dto.request.ComWechatRobotReqDTO;
import com.openwjk.central.remote.dto.request.RequestDTO;
import com.openwjk.central.remote.dto.response.ComWechatRobotRespDTO;
import com.openwjk.central.remote.enums.RemoteTypeEnum;
import com.openwjk.central.remote.helper.ConfigHelper;
import com.openwjk.central.remote.service.IDataService;
import com.openwjk.commons.utils.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 11:01
 */
@Service
public class MarkDownRobotDataHandler implements IDataService {
    @Autowired
    ConfigHelper configHelper;

    @Override
    public RemoteTypeEnum getCode() {
        return RemoteTypeEnum.COM_WECHAT_MARK_DOWN_ROBOT;
    }

    @Override
    public void buildRequest(Context context) {
        ComWechatRobotReqDTO robot = (ComWechatRobotReqDTO) context.getQueryDTO();
        RequestDTO requestDTO = new RequestDTO();
        Map<String, Object> map = new HashMap<>();
        Map<String, String> markdownMap = new HashMap<>();
        map.put("msgtype", "markdown");
        map.put("markdown", markdownMap);
        markdownMap.put("content", robot.getVerbalTrick());
        requestDTO.setBodyParam(JSON.toJSONString(map));
        CtConfigDO config = configHelper.getConfigByGroupAndCode(CtConfigGroupEnum.COM_WE_CHAT_ROBOT.name(), robot.getRobotEnum().getCode());
        requestDTO.setUrl(config.getValue());
        context.setRequestDTO(requestDTO);
    }

    @Override
    public void enterCache(CacheableResultDTO resultDTO) {

    }

    @Override
    public Boolean buildResponse(String resp) {
        if (StringUtils.isNotBlank(resp)) {
            ComWechatRobotRespDTO respDTO = JSON.parseObject(resp, ComWechatRobotRespDTO.class);
            if (resp != null && StringUtils.equals(Constant.STRING_ZERO, respDTO.getErrcode())) {
                return true;
            }
        }
        return true;
    }
}
