/**
 * 
 */
package org.mspring.mlog.service.impl.standard;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.security.TreeItem;
import org.mspring.mlog.service.impl.AbstractInstallService;
import org.mspring.mlog.service.security.TreeItemService;
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

        items.add(new TreeItem("1", "内容", "0", "", TreeItem.Type.TREE_FOLDER, true));
        items.add(new TreeItem("105", "文章管理", "1", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("105005", "列表", "105", "/admin/post/list", TreeItem.Type.TAB, true));
        items.add(new TreeItem("105010", "增加", "105", "/admin/post/create", TreeItem.Type.TAB, false));
        items.add(new TreeItem("105015", "修改", "105", "/admin/post/edit", TreeItem.Type.TAB, false));

        items.add(new TreeItem("110", "分类管理", "1", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("110005", "列表", "110", "/admin/catalog/list", TreeItem.Type.TAB, true));
        items.add(new TreeItem("110010", "增加", "110", "/admin/catalog/create", TreeItem.Type.TAB, false));
        items.add(new TreeItem("110015", "修改", "110", "/admin/catalog/edit", TreeItem.Type.TAB, false));

        items.add(new TreeItem("115", "评论管理", "1", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("115005", "评论管理", "115", "/admin/comment/list", TreeItem.Type.TAB, true));

        items.add(new TreeItem("120", "相册管理", "1", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("120005", "相册列表", "120", "/admin/album/list", TreeItem.Type.TAB, true));
        items.add(new TreeItem("120010", "增加相册", "120", "/admin/album/create", TreeItem.Type.TAB, false));
        items.add(new TreeItem("120015", "修改相册", "120", "/admin/album/edit", TreeItem.Type.TAB, false));
        items.add(new TreeItem("120020", "查看图片", "120", "/admin/photo/list", TreeItem.Type.TAB, false));
        items.add(new TreeItem("120025", "图片上传", "120", "/admin/photo/upload", TreeItem.Type.TAB, false));
        items.add(new TreeItem("120030", "相册设置", "120", "/admin/album/config", TreeItem.Type.TAB, false));

        /**********************************************************************************************************/

        items.add(new TreeItem("2", "外观", "0", "", TreeItem.Type.TREE_FOLDER, true));
        items.add(new TreeItem("205", "皮肤设置", "2", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("205005", "皮肤设置", "205", "/admin/setting/skin", TreeItem.Type.TAB, true));

        items.add(new TreeItem("210", "皮肤编辑", "2", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("210005", "皮肤编辑", "210", "/admin/system/skin/list", TreeItem.Type.TAB, true));

        /**********************************************************************************************************/

        items.add(new TreeItem("3", "用户", "0", "", TreeItem.Type.TREE_FOLDER, true));
        items.add(new TreeItem("305", "用户管理", "3", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("305005", "列表", "305", "/admin/user/list", TreeItem.Type.TAB, true));
        items.add(new TreeItem("305010", "新增", "305", "/admin/user/create", TreeItem.Type.TAB, false));
        items.add(new TreeItem("305015", "修改", "305", "/admin/user/edit", TreeItem.Type.TAB, false));

        items.add(new TreeItem("310", "角色", "3", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("310005", "列表", "310", "/admin/role/list", TreeItem.Type.TAB, true));
        items.add(new TreeItem("310010", "新增", "310", "/admin/role/create", TreeItem.Type.TAB, false));
        items.add(new TreeItem("310015", "修改", "310", "/admin/role/edit", TreeItem.Type.TAB, false));
        items.add(new TreeItem("310020", "授权", "310", "/admin/role/authorize", TreeItem.Type.TAB, false));

        /**********************************************************************************************************/

        // items.add(new TreeItem("31", "SNS", "0", "",
        // TreeItem.Type.TREE_FOLDER, true));
        // items.add(new TreeItem("3105", "站点", "31", "",
        // TreeItem.Type.TREE_ITEM, true));

        /**********************************************************************************************************/

        items.add(new TreeItem("4", "设置", "0", "", TreeItem.Type.TREE_FOLDER, true));

        items.add(new TreeItem("405", "个人信息", "4", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("405005", "个人信息", "405", "/admin/self/info", TreeItem.Type.TAB, true));

        items.add(new TreeItem("410", "博客信息", "4", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("410005", "博客信息", "410", "/admin/setting/bloginfo", TreeItem.Type.TAB, true));

        items.add(new TreeItem("415", "SEO设置", "4", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("415005", "SEO设置", "415", "/admin/setting/seo", TreeItem.Type.TAB, true));

        items.add(new TreeItem("420", "邮件设置", "4", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("420005", "邮件", "420", "/admin/system/mail/setting", TreeItem.Type.TAB, true));
        items.add(new TreeItem("420010", "邮件测试", "420", "/admin/system/mail/test", TreeItem.Type.TAB, false));

        items.add(new TreeItem("425", "任务管理", "4", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("425005", "列表", "425", "/admin/system/job/list", TreeItem.Type.TAB, true));
        items.add(new TreeItem("425010", "任务日志", "425", "/admin/system/job/log", TreeItem.Type.TAB, false));

        items.add(new TreeItem("430", "固定连接", "4", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("430005", "固定连接", "430", "/admin/system/permalink/config", TreeItem.Type.TAB, true));

        items.add(new TreeItem("435", "缓存管理", "4", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("435005", "缓存管理", "435", "/admin/system/cache/config", TreeItem.Type.TAB, true));

        /**********************************************************************************************************/

        items.add(new TreeItem("5", "运营", "0", "", TreeItem.Type.TREE_FOLDER, true));

        items.add(new TreeItem("505", "链接管理", "5", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("505005", "列表", "505", "/admin/link/list", TreeItem.Type.TAB, true));
        items.add(new TreeItem("505010", "增加", "505", "/admin/link/create", TreeItem.Type.TAB, false));
        items.add(new TreeItem("505015", "修改", "505", "/admin/link/edit", TreeItem.Type.TAB, false));
        items.add(new TreeItem("505020", "链接分类", "505", "/admin/linkType/list", TreeItem.Type.TAB, false));
        items.add(new TreeItem("505025", "新增分类", "505", "/admin/linkType/create", TreeItem.Type.TAB, false));
        items.add(new TreeItem("505030", "修改分类", "505", "/admin/linkType/edit", TreeItem.Type.TAB, false));

        items.add(new TreeItem("8", "工具", "0", "", TreeItem.Type.TREE_FOLDER, true));
        items.add(new TreeItem("805", "金山快盘", "8", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("805005", "金山快盘", "805", "/admin/api/kuaipan/setting", TreeItem.Type.TAB, true));

        items.add(new TreeItem("9", "关于", "0", "", TreeItem.Type.TREE_FOLDER, true));
        items.add(new TreeItem("905", "关于", "9", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("905005", "关于", "905", "/admin/about", TreeItem.Type.TAB, true));
        items.add(new TreeItem("910", "联系我们", "9", "", TreeItem.Type.TREE_ITEM, true));
        items.add(new TreeItem("910005", "联系我们", "910", "/admin/contact", TreeItem.Type.TAB, true));

        for (TreeItem item : items) {
            treeItemService.createItem(item);
        }
    }

}
