package org.mspring.platform.security.interceptor;

import org.mspring.platform.security.Initializable;

public abstract interface Interceptor extends Initializable {
    public abstract void destroy();
}