package org.mspring.platform.security.controller;

import org.mspring.platform.security.Initializable;

public abstract interface SecurityController extends Initializable
{
  public static final String DEFAULT_CONTROLLER = "org.mspring.platform.security.controller.DefaultSecurityController";
  //(1.class$com$aoc$sentry$controller$DefaultSecurityController == null ? 
  //(1.class$com$aoc$sentry$controller$DefaultSecurityController = 1.class$("org.mspring.platform.security.controller.DefaultSecurityController")) 
  //: 1.class$com$aoc$sentry$controller$DefaultSecurityController).getName();

  public abstract boolean isSecurityEnabled();
}