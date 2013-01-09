package org.mspring.platform.security.auth;

public class AuthenticationContext {
    private static final ThreadLocal context = new ThreadLocal();

    public static Object getUser() {
        return context.get();
    }

    public static void setUser(Object user) {
        context.set(user);
    }

    public static void clearUser() {
        setUser(null);
    }
}