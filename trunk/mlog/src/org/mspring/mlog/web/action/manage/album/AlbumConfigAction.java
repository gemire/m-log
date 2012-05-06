/**
 * 
 */
package org.mspring.mlog.web.action.manage.album;

import java.util.Map;

import org.mspring.mlog.web.action.manage.AbstractManageAction;

/**
 * @author Gao Youbo
 * @since Jan 12, 2012
 */
public class AlbumConfigAction extends AbstractManageAction {
    private Map<String, String> albumConfig;

    public Map<String, String> getAlbumConfig() {
        return albumConfig;
    }

    public void setAlbumConfig(Map<String, String> albumConfig) {
        this.albumConfig = albumConfig;
    }
    
    public String toEditAlbumConfig(){
        albumConfig = optionService.findAlbumConfig();
        return SUCCESS;
    }
    
    public String doEditAlbumConfig(){
        optionService.setOptions(albumConfig);
        return SUCCESS;
    }
}
