package org.mspring.platform.security.controller;

import java.util.Map;

import org.mspring.platform.security.config.SecurityConfig;

public class DefaultSecurityController implements SecurityController {
    public boolean isSecurityEnabled() {
        return true;
    }

    public void init(Map params, SecurityConfig config) {
    }
}