package com.openwjk.central.remote.service.impl.comwechat;

import com.openwjk.central.commons.domain.CacheableResultDTO;
import com.openwjk.central.commons.enums.ComWechatAppEnum;
import com.openwjk.central.commons.enums.ComWechatRobotEnum;
import com.openwjk.central.remote.dto.request.ComWechatRobotReqDTO;
import com.openwjk.central.commons.domain.CommonQueryReqDTO;
import com.openwjk.central.remote.dto.response.CommonQueryRespDTO;
import com.openwjk.central.remote.enums.RemoteTypeEnum;
import com.openwjk.central.remote.enums.RespTypeEnum;
import com.openwjk.central.remote.service.ComWechatService;
import com.openwjk.central.remote.service.CommonCacheQueryService;
import com.openwjk.commons.exception.CommonsException;
import com.openwjk.commons.utils.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 9:13
 */
@Service("comWechatService")
public class ComWechatServiceImpl implements ComWechatService {
    @Autowired
    CommonCacheQueryService queryService;

    @Override
    public CommonQueryRespDTO<Boolean> sendTextRobot(ComWechatRobotReqDTO robot) {
        if (robot == null || StringUtils.isBlank(robot.getVerbalTrick()) || robot.getRobotEnum() == null) {
            throw new CommonsException("param is invalid");
        }
        CommonQueryReqDTO reqDTO = new CommonQueryReqDTO(robot, Constant.EMPTY_STR, RemoteTypeEnum.COM_WECHAT_TEXT_ROBOT.name());
        CacheableResultDTO<Boolean> resultDTO = queryService.query(reqDTO);
        if (resultDTO.getEntity()) {
            return new CommonQueryRespDTO<>(RespTypeEnum.SUCCESS,resultDTO.getEntity());
        }else {
            return new CommonQueryRespDTO<>(RespTypeEnum.FAIL);
        }
    }

    @Override
    public CommonQueryRespDTO sendMarkDownRobot(ComWechatRobotReqDTO robot) {
        if (robot == null || StringUtils.isBlank(robot.getVerbalTrick()) || robot.getRobotEnum() == null) {
            throw new CommonsException("param is invalid");
        }
        CommonQueryReqDTO reqDTO = new CommonQueryReqDTO(robot, Constant.EMPTY_STR, RemoteTypeEnum.COM_WECHAT_MARK_DOWN_ROBOT.name());
        CacheableResultDTO<Boolean> resultDTO = queryService.query(reqDTO);
        if (resultDTO.getEntity()) {
            return new CommonQueryRespDTO<>(RespTypeEnum.SUCCESS,resultDTO.getEntity());
        }else {
            return new CommonQueryRespDTO<>(RespTypeEnum.FAIL);
        }
    }

    @Override
    public CommonQueryRespDTO getAppAccessToken(ComWechatAppEnum appEnum) {
        if (appEnum == null) {
            throw new CommonsException("param is invalid");
        }
        CommonQueryReqDTO reqDTO = new CommonQueryReqDTO(appEnum, appEnum.name(), RemoteTypeEnum.COM_WECHAT_ACCESS_TOKEN.name());
        CacheableResultDTO<Boolean> resultDTO = queryService.query(reqDTO);
        if (resultDTO.getEntity()) {
            return new CommonQueryRespDTO<>(RespTypeEnum.SUCCESS,resultDTO.getEntity());
        }else {
            return new CommonQueryRespDTO<>(RespTypeEnum.FAIL);
        }
    }
}
