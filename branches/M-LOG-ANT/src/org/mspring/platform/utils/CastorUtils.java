/**
 * 
 */
package org.mspring.platform.utils;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author Gao Youbo
 * @since Feb 20, 2012
 */
public class CastorUtils {
    public static <T> T castTo(String str, Class<?> clazz)
    {
      if (StringUtils.isBlank(str))
        return null;
      if (clazz.isAssignableFrom(Integer.class))
        return (T) NumberUtils.createInteger(str);
      if (clazz.isAssignableFrom(Long.class))
        return (T) NumberUtils.createLong(str);
      if (clazz.isAssignableFrom(Date.class))
        return (T) DateUtils.parse(str);
      if (clazz.isAssignableFrom(String.class))
        return (T) str;
      if (clazz.isAssignableFrom(String.class)) {
        if (str.contains(","))
          return (T) StringUtils.split(str, ",");
        if (str.contains("|")) {
          return (T) StringUtils.split(str, "|");
        }
        return (T) new String[] { str };
      }

      return null;
    }
}
