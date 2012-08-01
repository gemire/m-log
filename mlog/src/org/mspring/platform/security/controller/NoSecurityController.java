package org.mspring.platform.security.controller;

import java.util.Map;

import org.mspring.platform.security.config.SecurityConfig;

public class NoSecurityController implements SecurityController {
    public boolean isSecurityEnabled() {
        return false;
    }

    public void init(Map params, SecurityConfig config) {
    }
}