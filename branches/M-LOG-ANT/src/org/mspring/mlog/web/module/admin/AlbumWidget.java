/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.Album;
import org.mspring.mlog.service.AlbumService;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.support.log.Log;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 2012-10-15
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin/album")
public class AlbumWidget extends AbstractAdminWidget {

    @Autowired
    private AlbumService albumService;
    @Autowired
    private OptionService optionService;

    @RequestMapping({ "/list", "/", "" })
    @Log
    public String listAlbum(@ModelAttribute Page<Album> albumPage, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (albumPage == null) {
            albumPage = new Page<Album>();
        }
        albumPage.setSort(new Sort("sortOrder desc, createTime desc"));
        albumPage.setPageSize(14);

        albumPage = albumService.findAlbum(albumPage, "select album from Album album");

        model.addAttribute("albumPage", albumPage);
        return "/admin/album/listAlbum";
    }

    @RequestMapping({ "/create" })
    public String createAlbumView(@ModelAttribute Album album, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("types", Album.Type.getType());
        return "/admin/album/createAlbum";
    }

    @RequestMapping({ "/create/save" })
    @Log
    public String create_save(@ModelAttribute Album album, HttpServletRequest request, HttpServletResponse response, Model model) {
        albumService.createAlbum(album);
        return "redirect:/admin/album/list";
    }

    @RequestMapping("/delete")
    @Log
    public String deleteAlbum(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            return prompt(model, "请选择要删除的相册");
        }
        int count = albumService.getAlbumPhotoCount(id);
        if (count > 0) {
            return prompt(model, "无法删除该相册，相册下存在照片！");
        }
        albumService.deleteAlbum(id);
        return "redirect:/admin/album/list";
    }

    @RequestMapping("/edit")
    public String editAlbumView(@RequestParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            Object obj = getSessionAttribute(request, "AlbumWidget_edit_id");
            if (obj != null) {
                id = (Long) obj;
            }
        }
        setSessionAttribute(request, "AlbumWidget_edit_id", id);

        if (id == null) {
            return prompt(model, "请先选择要修改的相册");
        }
        Album album = albumService.getAlbumById(id);
        model.addAttribute("types", Album.Type.getType());
        model.addAttribute("album", album);
        return "/admin/album/editAlbum";
    }

    @RequestMapping("/edit/save")
    @Log
    public String edit_save(@ModelAttribute Album album, HttpServletRequest request, HttpServletResponse response, Model model) {
        album.setModifyTime(new Date());
        albumService.updateAlbum(album);
        return "redirect:/admin/album/list";
    }

    @RequestMapping("config")
    public String configAlbumView(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        options = optionService.getOptions();
        return "/admin/album/albumConfig";
    }

    @RequestMapping("/config/save")
    @Log
    public String saveBloginfo(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        optionService.setOptions(options);
        return "redirect:/admin/album/config";
    }

}
