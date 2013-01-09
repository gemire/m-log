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
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.AbstractWidget;
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
public class AlbumWidget extends AbstractWidget{

    @Autowired
    private AlbumService albumService;
    @Autowired
    private OptionService optionService;

    @RequestMapping({ "/list", "/", "" })
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

    @RequestMapping({ "/create", "/", "" })
    public String createAlbumView(@ModelAttribute Album album, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("types", Album.Type.getType());
        return "/admin/album/createAlbum";
    }

    @RequestMapping({ "/doCreate", "/", "" })
    public String doCreateAlbum(@ModelAttribute Album album, HttpServletRequest request, HttpServletResponse response, Model model) {
        albumService.createAlbum(album);
        return "redirect:/admin/album/list";
    }

    @RequestMapping("/delete")
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
    public String editAlbumView(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response, Model model) {
        Album album = albumService.getAlbumById(id);
        model.addAttribute("types", Album.Type.getType());
        model.addAttribute("album", album);
        return "/admin/album/editAlbum";
    }

    @RequestMapping("/doEdit")
    public String doEditAlbum(@ModelAttribute Album album, HttpServletRequest request, HttpServletResponse response, Model model) {
        album.setModifyTime(new Date());
        albumService.updateAlbum(album);
        return "redirect:/admin/album/list";
    }

    @RequestMapping("config")
    public String configAlbumView(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        options = optionService.getOptions();
        return "/admin/album/albumConfig";
    }

    @RequestMapping("/saveConfig")
    public String saveBloginfo(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        optionService.setOptions(options);
        return "redirect:/admin/album/config";
    }

}
