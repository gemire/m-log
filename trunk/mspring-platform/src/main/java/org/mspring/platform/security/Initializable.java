// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Initializable.java

package org.mspring.platform.security;

import org.mspring.platform.security.config.SecurityConfig;
import java.util.Map;

public interface Initializable {
    public abstract void init(Map map, SecurityConfig securityconfig);
}
