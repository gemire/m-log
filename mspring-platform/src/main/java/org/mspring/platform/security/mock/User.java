package org.mspring.platform.security.mock;

import java.security.Principal;

public class User implements Principal {
    public String getId() {
        return "mock_user_id";
    }

    public String getName() {
        return "mockUser";
    }

    public String toString() {
        return "User[mockUser]";
    }
}