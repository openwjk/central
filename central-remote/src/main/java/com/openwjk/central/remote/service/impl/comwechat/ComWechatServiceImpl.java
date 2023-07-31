package com.openwjk.central.remote.service.impl.comwechat;

import com.openwjk.central.remote.dto.request.ComWechatRobotReqDTO;
import com.openwjk.central.remote.dto.request.CommonQueryReqDTO;
import com.openwjk.central.remote.dto.response.CommonQueryRespDTO;
import com.openwjk.central.remote.enums.RemoteTypeEnum;
import com.openwjk.central.remote.service.ComWechatService;
import com.openwjk.central.remote.service.QueryService;
import com.openwjk.commons.exception.CommonsException;
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
    QueryService queryService;

    @Override
    public CommonQueryRespDTO sendTextRobot(ComWechatRobotReqDTO robot) {
        if (robot == null || StringUtils.isBlank(robot.getVerbalTrick()) || robot.getRobotEnum() == null) {
            throw new CommonsException("param is invalid");
        }
        CommonQueryReqDTO reqDTO = new CommonQueryReqDTO(robot, RemoteTypeEnum.COM_WECHAT_TEXT_ROBOT);
        return queryService.query(reqDTO);
    }

    @Override
    public CommonQueryRespDTO sendMarkDownRobot(ComWechatRobotReqDTO robot) {
        if (robot == null || StringUtils.isBlank(robot.getVerbalTrick()) || robot.getRobotEnum() == null) {
            throw new CommonsException("param is invalid");
        }
        CommonQueryReqDTO reqDTO = new CommonQueryReqDTO(robot, RemoteTypeEnum.COM_WECHAT_MARK_DOWN_ROBOT);
        return queryService.query(reqDTO);
    }

    @Override
    public CommonQueryRespDTO getAppAccessToken(ComWechatRobotReqDTO robot) {
        if (robot == null || StringUtils.isBlank(robot.getVerbalTrick()) || robot.getRobotEnum() == null) {
            throw new CommonsException("param is invalid");
        }
        CommonQueryReqDTO reqDTO = new CommonQueryReqDTO(robot, RemoteTypeEnum.COM_WECHAT_MARK_DOWN_ROBOT);
        return queryService.query(reqDTO);
    }
}
