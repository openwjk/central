package com.openwjk.central.service.impl;

import com.openwjk.central.commons.domain.Result;
import com.openwjk.central.commons.snowflake.SnowflakeIDGenImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class SnowflakeService {
    private Logger logger = LoggerFactory.getLogger(SnowflakeService.class);
    @Autowired
    private SnowflakeIDGenImpl snowflakeIDGen;

    public Result getId() {
        return snowflakeIDGen.get();
    }
}
