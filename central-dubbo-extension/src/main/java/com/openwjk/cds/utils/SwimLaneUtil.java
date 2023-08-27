package com.openwjk.cds.utils;

import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.common.utils.StringUtils;

import java.io.*;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/27 9:27
 */
public class SwimLaneUtil {
    private static Boolean FLAG = false;
    private static String SWIM_LANE_NO = null;
    private static String SWIM_LOG = "true";
    private static final Logger logger = LoggerFactory.getLogger(SwimLaneUtil.class);

    public SwimLaneUtil() {
    }

    private static String getHostsSwimLaneNo(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("SWIM_LANE_NO")) {
                    String swimLaneNo = line.replace("SWIM_LANE_NO=", "").trim();
                    return swimLaneNo;
                }
            }
        } catch (IOException e) {
            logger.error("swimlane load balance io error:", e);
        }
        return null;
    }

    public static String getSwimLaneNo() {
        String swimLaneNo = null;
        String swimLog = null;
        if (!FLAG) {
            swimLaneNo = System.getenv("SWIM_LANE_NO");
            swimLog = System.getenv("SWIM_LOG");
            String osName = System.getProperty("os.name");
            String fileName;
            if ("linux".equalsIgnoreCase(osName)) {
                fileName = "/etc/hosts";
            } else {
                fileName = "C://WINDOWS//system32//drivers//etc//hosts";
            }

            if (StringUtils.isEmpty(swimLaneNo)) {
                swimLaneNo = getHostsSwimLaneNo(fileName);
            }

            FLAG = true;
            SWIM_LANE_NO = swimLaneNo;
            SWIM_LOG = swimLog;
        } else {
            swimLaneNo = SWIM_LANE_NO;
        }

        return swimLaneNo;
    }

    public static String getSwimLog() {
        return SWIM_LOG;
    }
}
