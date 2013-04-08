/**
 * 
 */
package org.mspring.mlog.api.teamtoy;

import org.mspring.mlog.api.teamtoy.Const.ActionNames;
import org.mspring.mlog.api.teamtoy.Const.ParamNames;

/**
 * @author Gao Youbo
 * @since 2013-3-14
 * @description
 * @TODO
 */
public class TeamtoyUtils {
    /**
     * 获取团队成员
     * 
     * @return
     */
    public static String team_members() {
        TeamtoyAPI api = new TeamtoyAPI();
        api.addParam(ParamNames.token, Const.TOKEN);
        api.addParam(ParamNames.action, ActionNames.team_members);
        return api.request();
    }

    /**
     * 获取todolist
     * 
     * @return
     */
    public static String todo_list() {
        TeamtoyAPI api = new TeamtoyAPI();
        api.addParam(ParamNames.token, Const.TOKEN);
        api.addParam(ParamNames.action, ActionNames.todo_list);
        api.addParam("count", "10");
        return api.request();
    }
}
