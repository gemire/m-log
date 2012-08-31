/**
 * 
 */
package org.mspring.mlog.web.api.t.service.impl;

import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mspring.mlog.web.api.t.original.TencentAPI;
import org.mspring.mlog.web.api.t.service.TService;
import org.springframework.stereotype.Service;

/**
 * @author Gao Youbo
 * @since 2012-8-29
 * @Description
 * @TODO
 */
@Service
public class TencentService implements TService {

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.web.api.t.service.TService#add(java.lang.String)
     */
    @Override
    public String add(String content) {
        // TODO Auto-generated method stub
        return TencentAPI.t_add(content);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.web.api.t.service.TService#list()
     */
    @Override
    public String list() {
        // TODO Auto-generated method stub
        String json = TencentAPI.statuses_broadcast_timeline_ids("0", "30", "3", "0", "0", "0");
        JSONObject jsonObject = JSONObject.fromObject(json);
        JSONArray infos = jsonObject.getJSONObject("data").getJSONArray("info");
        String ids = "";
        for (int i = 0; i < infos.size(); i++) {
            JSONObject info = infos.getJSONObject(i);
            ids += info.getString("id");
            if (i < infos.size() - 1) {
                ids += ",";
            }
        }

        String listJson = TencentAPI.t_list(ids);
        JSONArray weiboArray = JSONObject.fromObject(listJson).getJSONObject("data").getJSONArray("info");
        for (int i = 0; i < weiboArray.size(); i++) {
            JSONObject weiboJson = weiboArray.getJSONObject(i);
            Object obj = JSONObject.toBean(weiboJson);
            if (obj instanceof MorphDynaBean) {
                MorphDynaBean bean = (MorphDynaBean) obj;
                Object video = bean.get("video");
                if (video != null) {
                    System.out.println(video.getClass());
                }
            }
        }
        return null;
    }

}
