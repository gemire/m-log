/**
 * 
 */
package org.mspring.mlog.service.impl.standard;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.TreeItem;
import org.mspring.mlog.service.TreeItemService;
import org.mspring.mlog.service.impl.AbstractInstallService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2012-12-24
 * @Description
 * @TODO
 */
@Service
@Transactional
public class StandardInstallServiceImpl extends AbstractInstallService {

    private static final Logger log = Logger.getLogger(StandardInstallServiceImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.InstallService#initTreeItems()
     */
    @Override
    public void initTreeItems() {
        // TODO Auto-generated method stub
        log.debug("init tree items...");
        TreeItemService treeItemService = ServiceFactory.getTreeItemService();

        treeItemService.clearItems();

        List<TreeItem> items = new ArrayList<TreeItem>();
        items.add(new TreeItem("1", "功能菜单", "0", "", TreeItem.Type.TREE_FOLDER, true));
        
        items.add(new TreeItem("105", "首页", "1", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("105001", "首页", "105", "/admin/about", TreeItem.Type.TAB, true));
        
        items.add(new TreeItem("115", "文章", "1", "", TreeItem.Type.TREE_FOLDER, true));
        items.add(new TreeItem("11505", "文章管理", "115", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("11505005", "列表", "11505", "/admin/post/list", TreeItem.Type.TAB, true));
        items.add(new TreeItem("11505010", "增加", "11505", "/admin/post/create", TreeItem.Type.TAB, false));
        items.add(new TreeItem("11505015", "修改", "11505", "/admin/post/edit", TreeItem.Type.TAB, false));
        
        items.add(new TreeItem("11515", "分类管理", "115", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("11515005", "列表", "11515", "/admin/catalog/list", TreeItem.Type.TAB, true));
        items.add(new TreeItem("11515010", "增加", "11515", "/admin/catalog/create", TreeItem.Type.TAB, false));
        items.add(new TreeItem("11515015", "修改", "11515", "/admin/catalog/edit", TreeItem.Type.TAB, false));
        
        items.add(new TreeItem("11520", "评论管理", "115", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("11520005", "评论管理", "11520", "/admin/comment/list", TreeItem.Type.TAB, true));
        
        items.add(new TreeItem("120", "链接管理", "1", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("120005", "列表", "120", "/admin/link/list", TreeItem.Type.TAB, true));
        items.add(new TreeItem("120010", "增加", "120", "/admin/link/create", TreeItem.Type.TAB, false));
        items.add(new TreeItem("120015", "修改", "120", "/admin/link/edit", TreeItem.Type.TAB, false));
        items.add(new TreeItem("120020", "链接分类", "120", "/admin/linkType/list", TreeItem.Type.TAB, false));
        items.add(new TreeItem("120025", "新增分类", "120", "/admin/linkType/create", TreeItem.Type.TAB, false));
        items.add(new TreeItem("120030", "修改分类", "120", "/admin/linkType/edit", TreeItem.Type.TAB, false));
        
        
        items.add(new TreeItem("125", "相册管理", "1", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("125005", "相册列表", "125", "/admin/album/list", TreeItem.Type.TAB, true));
        items.add(new TreeItem("125010", "增加相册", "125", "/admin/album/create", TreeItem.Type.TAB, false));
        items.add(new TreeItem("125015", "修改相册", "125", "/admin/album/edit", TreeItem.Type.TAB, false));
        items.add(new TreeItem("125020", "查看图片", "125", "/admin/photo/list", TreeItem.Type.TAB, false));
        items.add(new TreeItem("125025", "图片上传", "125", "/admin/photo/upload", TreeItem.Type.TAB, false));
        items.add(new TreeItem("125030", "相册设置", "125", "/admin/album/config", TreeItem.Type.TAB, false));
        

        items.add(new TreeItem("2", "个人配置", "0", "", TreeItem.Type.TREE_FOLDER, true));
        items.add(new TreeItem("205", "用户信息", "2", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("205005", "用户信息", "205", "/admin/user/userinfo", TreeItem.Type.TAB, true));
        
        items.add(new TreeItem("220", "博客信息", "2", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("220005", "博客信息", "220", "/admin/setting/bloginfo", TreeItem.Type.TAB, true));
        
        items.add(new TreeItem("230", "皮肤设置", "2", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("230005", "博客信息", "230", "/admin/setting/skin", TreeItem.Type.TAB, true));
        
        items.add(new TreeItem("240", "SEO设置", "2", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("240005", "博客信息", "240", "/admin/setting/seo", TreeItem.Type.TAB, true));
        
        items.add(new TreeItem("245", "缓存管理", "2", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("245005", "博客信息", "245", "/admin/cache/setting", TreeItem.Type.TAB, true));

        items.add(new TreeItem("3", "系统配置", "0", "", TreeItem.Type.TREE_FOLDER, true));
        items.add(new TreeItem("325", "邮件设置", "3", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("325001", "邮件", "325", "/admin/system/mail/setting", TreeItem.Type.TAB, true));
        items.add(new TreeItem("325002", "邮件测试", "325", "/admin/system/mail/test", TreeItem.Type.TAB, false));
        
        items.add(new TreeItem("330", "任务管理", "3", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("330005", "列表", "330", "/admin/system/job/list", TreeItem.Type.TAB, true));
        items.add(new TreeItem("330010", "任务日志", "330", "/admin/system/job/log", TreeItem.Type.TAB, false));
        
        items.add(new TreeItem("335", "用户管理", "3", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("335005", "用户列表", "335", "/admin/about", TreeItem.Type.TAB, true));
        items.add(new TreeItem("335010", "新增用户", "335", "/admin/about", TreeItem.Type.TAB, true));
        items.add(new TreeItem("335015", "编辑用户", "335", "/admin/about", TreeItem.Type.TAB, true));
        items.add(new TreeItem("335020", "用户授权", "335", "/admin/about", TreeItem.Type.TAB, true));
        

        items.add(new TreeItem("4", "插件", "0", "", TreeItem.Type.TREE_FOLDER, true));
        items.add(new TreeItem("405", "金山快盘", "4", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("405005", "金山快盘", "405", "/admin/api/kuaipan/setting", TreeItem.Type.TAB, true));

        items.add(new TreeItem("9", "关于", "0", "", TreeItem.Type.TREE_FOLDER, true));
        items.add(new TreeItem("905", "关于", "9", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("905005", "金山快盘", "905", "/admin/about", TreeItem.Type.TAB, true));
        items.add(new TreeItem("910", "联系我们", "9", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("910005", "金山快盘", "910", "/admin/contact", TreeItem.Type.TAB, true));

        for (TreeItem item : items) {
            treeItemService.createItem(item);
        }
    }

}
