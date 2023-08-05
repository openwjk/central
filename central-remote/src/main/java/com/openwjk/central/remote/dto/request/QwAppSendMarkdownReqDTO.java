package com.openwjk.central.remote.dto.request;

import com.alibaba.fastjson2.annotation.JSONField;
import com.openwjk.central.commons.domain.BaseDomain;
import com.openwjk.central.commons.enums.QwAppEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/4 10:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QwAppSendMarkdownReqDTO  extends BaseQwAppSendMsgReqDTO {
    @JSONField(name = "markdown")
    private Content markdown;

}
