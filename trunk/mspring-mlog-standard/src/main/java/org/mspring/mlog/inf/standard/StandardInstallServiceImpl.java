/**
 * 
 */
package org.mspring.mlog.inf.standard;

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
        items.add(new TreeItem("1", "功能菜单", "0", "", false, true, "main"));
        items.add(new TreeItem("105", "首页", "1", "admin/about", false, true, "main"));
        items.add(new TreeItem("115", "文章", "1", "", false, true, "main"));
        items.add(new TreeItem("11505", "文章管理", "115", "admin/post/list", false, true, "main"));
        items.add(new TreeItem("11515", "分类管理", "115", "admin/catalog/list", false, true, "main"));
        items.add(new TreeItem("11520", "评论管理", "115", "admin/comment/list", false, true, "main"));
        items.add(new TreeItem("120", "链接管理", "1", "admin/link/list", false, true, "main"));
        items.add(new TreeItem("125", "相册管理", "1", "admin/album/list", false, true, "main"));

        items.add(new TreeItem("2", "配置", "0", "", false, true, "main"));
        items.add(new TreeItem("205", "用户信息", "2", "admin/user/userinfo", false, true, "main"));
        items.add(new TreeItem("220", "博客信息", "2", "admin/setting/bloginfo", false, true, "main"));
        items.add(new TreeItem("225", "邮件设置", "2", "admin/setting/mail", false, true, "main"));
        items.add(new TreeItem("230", "皮肤设置", "2", "admin/setting/skin", false, true, "main"));
        //items.add(new TreeItem("235", "BAE设置", "2", "admin/setting/bae", false, true, "main"));
        items.add(new TreeItem("240", "SEO设置", "2", "admin/setting/seo", false, true, "main"));
        items.add(new TreeItem("245", "缓存管理", "2", "admin/cache/setting", false, true, "main"));
        
        items.add(new TreeItem("4", "插件", "0", "", false, true, "main"));
        items.add(new TreeItem("405", "金山快盘", "4", "/admin/api/kuaipan/setting", false, true, "main"));

        items.add(new TreeItem("9", "关于", "0", "", false, true, "main"));
        items.add(new TreeItem("905", "关于", "9", "admin/about", false, true, "main"));
        items.add(new TreeItem("910", "联系我们", "9", "admin/contact", false, true, "main"));

        for (TreeItem item : items) {
            treeItemService.createItem(item);
        }
    }

}
