/**
 * 
 */
package org.mspring.mlog.web.module.admin.system;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.lang3.StringEscapeUtils;
import org.mspring.mlog.entity.Skin;
import org.mspring.mlog.service.SkinService;
import org.mspring.mlog.support.log.Log;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.admin.AbstractAdminWidget;
import org.mspring.platform.utils.ArrayUtils;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 2013-1-10
 * @Description
 * @TODO 系统配置
 */
@Widget
@RequestMapping("/admin/system/skin")
public class SkinWidget extends AbstractAdminWidget {
    @Autowired
    private SkinService skinService;

    @RequestMapping("/list")
    public String list(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Skin> skins = skinService.scrnSkin();
        model.addAttribute("skins", skins);
        return "/admin/system/skin/listSkin";
    }

    @RequestMapping("/edit_files")
    public String edit_files(@RequestParam(required = false) String skin, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        if (StringUtils.isBlank(skin)) {
            return prompt(model, "请选择要编辑的主题");
        }
        // 允许编辑的文件类型
        final String[] allowExts = new String[] { "ftl", "css", "JS", "html", "htm" };
        // 主题文件夹
        String skinFolderPath = request.getSession().getServletContext().getRealPath("/skins/" + skin);
        File skinFolder = new File(skinFolderPath);

        // 检索出所有可编辑的文件
        Iterator<File> files = FileUtils.iterateFilesAndDirs(skinFolder, new IOFileFilter() {
            @Override
            public boolean accept(File file, String arg1) {
                // TODO Auto-generated method stub
                return true;
            }

            @Override
            public boolean accept(File file) {
                // TODO Auto-generated method stub
                String ext = StringUtils.getFileExtend(file.getName());
                return ArrayUtils.ignoreCaseContains(allowExts, ext);
            }
        }, new IOFileFilter() {

            @Override
            public boolean accept(File arg0, String arg1) {
                // TODO Auto-generated method stub
                return true;
            }

            @Override
            public boolean accept(File file) {
                // TODO Auto-generated method stub
                if (file.isDirectory()) {
                    File[] files = file.listFiles();
                    for (File f : files) {
                        String ext = StringUtils.getFileExtend(f.getName());
                        if (f.isDirectory()) {
                            return accept(f);
                        }
                        if (ArrayUtils.ignoreCaseContains(allowExts, ext)) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    return true;
                }
            }
        });

        // 将文件列表封装成ztree可用的json格式数据
        String contextPath = request.getContextPath();
        StringBuffer treeJson = new StringBuffer();
        while (files.hasNext()) {
            File file = files.next();
            String skinFilePath = file.getPath();
            String folderPath = skinFolder.getPath();
            String absolutePath = StringUtils.replace(skinFilePath, folderPath, "");
            absolutePath = URLEncoder.encode(absolutePath, "UTF-8");
            treeJson.append("{ id:'" + file.getName() + "', pId:'" + file.getParentFile().getName() + "', name:'" + file.getName() + "', url:'" + contextPath + "/admin/system/skin/edit_main?skin=" + skin + "&path=" + absolutePath + "', target:'skin_edit_main', open:true }\n,");
        }
        model.addAttribute("files", files);
        model.addAttribute("treeJson", treeJson);
        return "/admin/system/skin/edit_files";
    }

    @RequestMapping("/edit_main")
    public String edit_main(@RequestParam(required = false) String skin, @RequestParam(required = false) String path, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        if (StringUtils.isBlank(skin)) {
            return prompt(model, "请选择要编辑的主题");
        }
        if (StringUtils.isBlank(path)) {
            path = "/index.ftl";
        }
        String relativePath = "/skins/" + skin + path;
        String editFilePath = request.getSession().getServletContext().getRealPath(relativePath);
        File editFile = new File(editFilePath);
        if (!editFile.exists()) {
            return prompt(model, "系统没有找到文件" + relativePath);
        }
        String content = FileUtils.readFileToString(editFile, Charset.forName("UTF-8"));
        content = StringEscapeUtils.escapeHtml4(content);

        model.addAttribute("content", content);
        model.addAttribute("skin", skin);
        model.addAttribute("path", path);
        return "/admin/system/skin/edit_main";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam(required = false) String skin, @RequestParam(required = false) String path, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        if (StringUtils.isBlank(skin)) {
            return prompt(model, "请选择要编辑的主题");
        }
        if (StringUtils.isBlank(path)) {
            path = "/index.ftl";
        }
        model.addAttribute("skin", skin);
        model.addAttribute("path", path);
        return "/admin/system/skin/editSkin";
    }

    @RequestMapping("/edit/save")
    @Log
    public String edit_save(@RequestParam(required = false) String skin, @RequestParam(required = false) String path, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String content = request.getParameter("content");
        String relativePath = "/skins/" + skin + path;
        String editFilePath = request.getSession().getServletContext().getRealPath(relativePath);
        File editFile = new File(editFilePath);
        FileUtils.writeStringToFile(editFile, content, Charset.forName("UTF-8"));
        return edit_main(skin, path, request, response, model);
    }
}
