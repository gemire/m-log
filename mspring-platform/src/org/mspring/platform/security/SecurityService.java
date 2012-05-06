// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SecurityService.java

package org.mspring.platform.security;

import java.util.Set;
import javax.servlet.http.HttpServletRequest;

// Referenced classes of package org.mspring.platform.security:
//			Initializable

public interface SecurityService
	extends Initializable
{

	public abstract void destroy();

	public abstract Set getRequiredRoles(HttpServletRequest httpservletrequest);
}
