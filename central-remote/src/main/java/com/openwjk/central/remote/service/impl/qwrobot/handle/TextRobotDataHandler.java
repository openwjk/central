package com.openwjk.central.remote.service.impl.qwrobot.handle;

import com.alibaba.fastjson2.JSON;
import com.openwjk.central.commons.domain.CacheableResultDTO;
import com.openwjk.central.commons.enums.CtConfigGroupEnum;
import com.openwjk.central.dao.model.ConfigDO;
import com.openwjk.central.remote.dto.Context;
import com.openwjk.central.remote.dto.request.QwRobotReqDTO;
import com.openwjk.central.remote.dto.request.RequestDTO;
import com.openwjk.central.remote.dto.response.QwRobotRespDTO;
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
 * @date 2023/7/30 11:00
 */
@Service
public class TextRobotDataHandler implements IDataService {
    @Autowired
    ConfigHelper configHelper;

    @Override
    public RemoteTypeEnum getCode() {
        return RemoteTypeEnum.QW_TEXT_ROBOT;
    }

    @Override
    public void buildRequest(Context context) {
        QwRobotReqDTO robot = (QwRobotReqDTO) context.getQueryDTO();
        RequestDTO requestDTO = new RequestDTO();
        Map<String, Object> map = new HashMap<>();
        Map<String, String> textMap = new HashMap<>();
        map.put("msgtype", "text");
        map.put("text", textMap);
        textMap.put("content", robot.getVerbalTrick());
        requestDTO.setBodyParam(JSON.toJSONString(map));
        ConfigDO config = configHelper.getConfigByGroupAndCode(CtConfigGroupEnum.QW_ROBOT.name(), robot.getRobotEnum().getCode());
        requestDTO.setUrl(config.getValue());
        context.setRequestDTO(requestDTO);
    }

    @Override
    public void enterCache(CacheableResultDTO resultDTO) {

    }

    @Override
    public Boolean buildResponse(String resp) {
        if (StringUtils.isNotBlank(resp)) {
            QwRobotRespDTO respDTO = JSON.parseObject(resp, QwRobotRespDTO.class);
            if (resp != null && StringUtils.equals(Constant.STRING_ZERO, respDTO.getErrcode())) {
                return true;
            }
        }
        return false;
    }
}
