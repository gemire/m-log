/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.Theme;

/**
 * @author Gao Youbo
 * @since Feb 8, 2012
 */
public interface ThemeService {
    public List<Theme> _findAllTheme(String basePath);
    public void useThisTheme(String folderName);
}
