package com.openwjk.central.service.impl;

import com.openwjk.central.commons.utils.Constants;
import com.openwjk.central.commons.utils.RedisUtil;
import com.openwjk.central.dao.mapper.AccountDOMapper;
import com.openwjk.central.dao.mapper.AccountTypeDOMapper;
import com.openwjk.central.dao.model.AccountDO;
import com.openwjk.central.dao.model.AccountDOExample;
import com.openwjk.central.dao.model.AccountTypeDO;
import com.openwjk.central.dao.model.AccountTypeDOExample;
import com.openwjk.central.service.domain.req.LoginAccountReqVO;
import com.openwjk.central.service.service.AccountService;
import com.openwjk.commons.enums.ResponseEnum;
import com.openwjk.commons.exception.ParamInvalidException;
import com.openwjk.commons.utils.Constant;
import com.openwjk.commons.utils.EncryptUtil;
import com.openwjk.commons.utils.RandomCodeUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/15 9:52
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDOMapper accountDOMapper;
    @Autowired
    private AccountTypeDOMapper accountTypeDOMapper;
    @Autowired
    RedisUtil redisUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String registerAccount(LoginAccountReqVO reqVO, String type) {
        List<AccountTypeDO> accountTypeDOS = getAccountType(reqVO, type);
        if (CollectionUtils.isNotEmpty(accountTypeDOS)) {
            throw new ParamInvalidException("", "", null, "该账号已存在，请重新输入账号！");
        }
        String uId = RandomCodeUtil.getUuId();
        String password = EncryptUtil.md5(EncryptUtil.md5(reqVO.getPassword()));
        AccountDO accountDO = new AccountDO();
        accountDO.setuId(uId);
        accountDO.setPassword(password);

        AccountTypeDO typeDO = new AccountTypeDO();
        typeDO.setAccount(reqVO.getAccount());
        typeDO.setType(type);
        typeDO.setuId(uId);
        try {
            accountDOMapper.insertSelective(accountDO);
            accountTypeDOMapper.insertSelective(typeDO);
        } catch (DuplicateKeyException e) {
            throw new ParamInvalidException("", "", null, "该账号已存在，请重新输入账号！");
        }
        return ResponseEnum.SUCCESS.getMsg();
    }

    private List<AccountTypeDO> getAccountType(LoginAccountReqVO reqVO, String type) {
        AccountTypeDOExample typeDOExample = new AccountTypeDOExample();
        typeDOExample.createCriteria().andAccountEqualTo(reqVO.getAccount()).andTypeEqualTo(type).andIsDeletedEqualTo(Constant.STR_N);
        return accountTypeDOMapper.selectByExample(typeDOExample);
    }

    @Override
    public String loginAccount(LoginAccountReqVO reqVO, String type) {
        List<AccountTypeDO> accountTypeDOS = getAccountType(reqVO, type);
        if (CollectionUtils.isEmpty(accountTypeDOS)) {
            throw new ParamInvalidException("", "", null, "该账号不存在，请重新输入账号或注册！");
        }
        AccountTypeDO accountType = accountTypeDOS.get(Constant.INT_ZERO);
        String uId = accountType.getuId();
        String password = EncryptUtil.md5(EncryptUtil.md5(reqVO.getPassword()));
        AccountDOExample example = new AccountDOExample();
        example.createCriteria().andUIdEqualTo(uId).andPasswordEqualTo(password).andIsDeletedEqualTo(Constant.STR_N);
        List<AccountDO> accountDOS = accountDOMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(accountDOS)) {
            throw new ParamInvalidException("", "", null, "密码错误请重新输入");
        }
        AccountDO account = accountDOS.get(Constant.INT_ZERO);
        if (account != null && !StringUtils.equals(Constant.STRING_ZERO, account.getStatus())) {
            if (StringUtils.equals(account.getStatus(), Constant.STRING_ONE)) {
                throw new ParamInvalidException("", "", null, "账号已被封号，禁止登录！");
            }
            if (StringUtils.equals(account.getStatus(), Constant.STRING_TWO)) {
                throw new ParamInvalidException("", "", null, "账号已被冻结，请解冻后再登录！");
            }
        }
        String token = EncryptUtil.md5(RandomCodeUtil.getUuId());
        redisUtil.set(token, uId + Constant.SPLIT_UNION + accountType.getAccount() + Constant.SPLIT_UNION + type, Constant.LONG_THIRTY, TimeUnit.MINUTES);
        return token;
    }

    @Override
    public void logout(String token) {
        redisUtil.del(token);
    }
}
