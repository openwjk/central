package com.openwjk.central.service.impl;

import com.openwjk.central.service.service.SystemService;
import org.springframework.stereotype.Service;

@Service
public class SystemServiceImpl implements SystemService {
    @Override
    public String checkRun() {
        return "success.";
    }
}
