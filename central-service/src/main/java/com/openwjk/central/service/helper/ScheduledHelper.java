package com.openwjk.central.service.helper;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronExpression;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/28 13:04
 */
@Service
public class ScheduledHelper {
    public boolean checkIsRun(String corn, Date curDate){
        CronExpression exp = null;
        Boolean inCron=false;
        try {
            if(StringUtils.isNotBlank(corn)&& CronExpression.isValidExpression(corn)){
                exp = new CronExpression(corn);
                inCron = exp.isSatisfiedBy(curDate) ;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return inCron;
    }
}
