/**
 * 
 */
package org.mspring.platform.web.widget.tag;

import java.io.Writer;

/**
 * @author Gao Youbo
 * @since 2012-7-17
 * @Description
 * @TODO
 */
public class WidgetProcessor {

    private WidgetCallback callback;

    /**
     * @param callback
     *            the callback to set
     */
    public void setCallback(WidgetCallback callback) {
        this.callback = callback;
    }

    public void process(Writer writer) {
        
    }

}
