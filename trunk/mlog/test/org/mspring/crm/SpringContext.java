/**
 * 
 */
package org.mspring.crm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Gao Youbo
 * @since Feb 20, 2012
 */
public class SpringContext {
    private static ApplicationContext ac = null;
    
    public static void initApplicationContext() {
        if (ac == null) {
            String[] configLocations = new String[] {"applicationContext.xml"};
            ac = new ClassPathXmlApplicationContext(configLocations);
        }
    }

    public static ApplicationContext getContext() {
        if (ac == null) {
            initApplicationContext();
        }
        return ac;
    }
}
