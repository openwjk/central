package com.openwjk.central.service.impl;

import com.openwjk.central.service.service.SystemService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SystemServiceImpl implements SystemService {
    @Override
    public String checkRun() {
        return "success.";
    }

    @Override
    @Cacheable(cacheNames = "default",key = "#id")
    public String redisTest(String id) {
        return "success";
    }

}
