package com.openwjk.central.remote.service.impl.qwrobot;

import com.openwjk.central.commons.domain.CacheableResultDTO;
import com.openwjk.central.remote.dto.request.QwRobotReqDTO;
import com.openwjk.central.commons.domain.CommonQueryReqDTO;
import com.openwjk.central.remote.dto.response.CommonQueryRespDTO;
import com.openwjk.central.remote.enums.RemoteTypeEnum;
import com.openwjk.central.remote.enums.RespTypeEnum;
import com.openwjk.central.remote.service.QwRobotService;
import com.openwjk.central.remote.service.CommonCacheQueryService;
import com.openwjk.commons.exception.CommonsException;
import com.openwjk.commons.utils.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangjunkai
 * @description 企业微信机器人
 * @date 2023/7/30 9:13
 */
@Service("qwRobotService")
public class QwRobotServiceImpl implements QwRobotService {
    private static final String SIMPLE_CACHE = "simpleCache";

    @Autowired
    CommonCacheQueryService queryService;

    @Override
    public CommonQueryRespDTO<Boolean> sendTextRobot(QwRobotReqDTO robot) {
        if (robot == null || StringUtils.isBlank(robot.getVerbalTrick()) || robot.getRobotEnum() == null) {
            throw new CommonsException("param is invalid");
        }
        CommonQueryReqDTO reqDTO = new CommonQueryReqDTO(robot, SIMPLE_CACHE,Constant.EMPTY_STR, RemoteTypeEnum.QW_TEXT_ROBOT.name());
        CacheableResultDTO<Boolean> resultDTO = queryService.query(reqDTO);
        if (resultDTO.getEntity()) {
            return new CommonQueryRespDTO<>(RespTypeEnum.SUCCESS, resultDTO.getEntity());
        } else {
            return new CommonQueryRespDTO<>(RespTypeEnum.FAIL);
        }
    }

    @Override
    public CommonQueryRespDTO sendMarkDownRobot(QwRobotReqDTO robot) {
        if (robot == null || StringUtils.isBlank(robot.getVerbalTrick()) || robot.getRobotEnum() == null) {
            throw new CommonsException("param is invalid");
        }
        CommonQueryReqDTO reqDTO = new CommonQueryReqDTO(robot, SIMPLE_CACHE, Constant.EMPTY_STR, RemoteTypeEnum.QW_MARK_DOWN_ROBOT.name());
        CacheableResultDTO<Boolean> resultDTO = queryService.query(reqDTO);
        if (resultDTO.getEntity()) {
            return new CommonQueryRespDTO<>(RespTypeEnum.SUCCESS, resultDTO.getEntity());
        } else {
            return new CommonQueryRespDTO<>(RespTypeEnum.FAIL);
        }
    }

}
