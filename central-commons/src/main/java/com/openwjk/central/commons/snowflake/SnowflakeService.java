package com.openwjk.central.commons.snowflake;

import com.openwjk.central.commons.domain.Result;
import com.openwjk.central.commons.domain.Status;
import com.openwjk.central.commons.exception.InitException;
import com.openwjk.central.commons.factory.PropertyFactory;
import com.openwjk.central.commons.utils.Constants;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


public class SnowflakeService {
    private Logger logger = LoggerFactory.getLogger(SnowflakeService.class);
    private static SnowflakeService snowflakeService;
    private static SnowflakeIDGenImpl snowflakeIDGen;

    @SneakyThrows
    private SnowflakeService() {
        Properties properties = PropertyFactory.getProperties();
        String zkAddress = String.valueOf(properties.get(Constants.LEAF_SNOWFLAKE_ZK_ADDRESS));
        int port = Integer.valueOf((String) properties.get(Constants.LEAF_SNOWFLAKE_PORT));
        snowflakeIDGen = new SnowflakeIDGenImpl(zkAddress, port);
        if (snowflakeIDGen.init()) {
            logger.info("Snowflake Service Init Successfully");
        } else {
            throw new InitException("Snowflake Service Init Fail");
        }
    }


    public static final SnowflakeService getInstance() {
        if (snowflakeIDGen == null) {
            synchronized (SnowflakeService.class) {
                if (snowflakeIDGen == null) {
                    snowflakeService = new SnowflakeService();
                }
            }
        }
        return snowflakeService;
    }


    public Long getId() {
        Result result = snowflakeIDGen.get();
        if (Status.SUCCESS.name().equals(result.getStatus().name())) {
            return result.getId();
        }
        throw new RuntimeException("获取snowflakeId失败");
    }
}
