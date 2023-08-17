package com.openwjk.central.web.helper;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/17 14:45
 */
@Component
public class IgnoreUrlHelper {
    public static final String INIT_PARAM_KEY_IGNORE_URLS = "ignoreUrls";

    public List<String> initIgnoreUrls(String ignoreUrlStr) {
        List<String> ignoreUrls = new ArrayList<>();
        if (StringUtils.isNotBlank(ignoreUrlStr)) {
            String[] arr = ignoreUrlStr.split(",");
            ignoreUrls.addAll(Arrays.asList(arr));
        }
        return ignoreUrls;
    }

    public boolean isIgnoreUrl(HttpServletRequest request, List<String> ignoreUrls) {
        if (CollectionUtils.isEmpty(ignoreUrls)) {
            return false;
        }

        UrlPathHelper urlPathHelper = new UrlPathHelper();
        PathMatcher pathMatcher = new AntPathMatcher();

        String lookupPath = urlPathHelper.getLookupPathForRequest(request);
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, lookupPath)) {
                return true;
            }
        }
        return false;
    }
}
