package org.mspring.platform.api.kuaipan.json;

public interface JSONErrorListener {
    void start(String text);
    void error(String message, int column);
    void end();
}