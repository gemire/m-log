/**
 * 
 */
package org.mspring.mlog.web.module.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mspring.mlog.common.PageNames;
import org.mspring.mlog.entity.Album;
import org.mspring.mlog.entity.Photo;
import org.mspring.mlog.service.AlbumService;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.PhotoService;
import org.mspring.mlog.utils.PermissionUtils;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 2012-11-23
 * @Description
 * @TODO
 */
@Widget("webAlbumWidget")
@RequestMapping("/album")
public class AlbumWidget extends AbstractWebWidget {
    @Autowired
    private OptionService optionService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private PhotoService photoService;

    @RequestMapping("/list")
    public String listAlbum(@ModelAttribute Page<Album> albumPage, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (albumPage == null) {
            albumPage = new Page<Album>();
        }
        if (albumPage.getSort() == null) {
            albumPage.setSort(new Sort("sortOrder desc, createTime desc"));
        }
        String pageSize = optionService.getOption("web_albumlist_size");
        if (StringUtils.isNotBlank(pageSize)) {
            albumPage.setPageSize(Integer.parseInt(pageSize));
        }

        albumService.findAlbum(albumPage, "select album from Album album where album.type <> ?", Album.Type.PRIVATE);
        model.addAttribute(FreemarkerVariableNames.ALBUM_PAGE, albumPage);
        setCurrnetPage(model, PageNames.ALBUM);
        return "skin:/album";
    }

    /**
     * 相册授权
     * 
     * @param photoPage
     * @param album
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/token")
    public String albumToken(@RequestParam(required = false) Long albumId, @RequestParam(required = false) String password, HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
        boolean has_permission = PermissionUtils.hasAlbumPermission(albumId, password);
        if (!has_permission) {
            model.addAttribute("albumId", albumId);
            model.addAttribute("not_token", false);
            return "skin:/album-token";
        }
        PermissionUtils.setAlbumPermission(albumId, password, session);
        return String.format("redirect:/album/album-%s-1.html", albumId);
        // return listPhoto(null, albumId, request, response, session, model);
    }

    @RequestMapping("/photo/list")
    public String listPhoto(@ModelAttribute Page<Photo> photoPage, @RequestParam(required = false) Long album, HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
        // 判断权限
        boolean has_permission = PermissionUtils.hasAlbumPermission(album, session);
        if (!has_permission) {
            model.addAttribute("albumId", album);
            return "skin:/album-token";
        }
        if (photoPage == null) {
            photoPage = new Page<Photo>();
        }
        if (photoPage.getSort() == null) {
            photoPage.setSort(new Sort("id", Sort.DESC));
        }
        String pageSize = optionService.getOption("web_photolist_size");
        if (StringUtils.isNotBlank(pageSize)) {
            photoPage.setPageSize(Integer.parseInt(pageSize));
        }

        photoService.findPhoto(photoPage, "select photo from Photo photo where photo.album.id = ?", album);
        Album albumObject = albumService.getAlbumById(album);
        model.addAttribute(FreemarkerVariableNames.PHOTO_PAGE, photoPage);
        model.addAttribute(FreemarkerVariableNames.ALBUM, albumObject);
        setCurrnetPage(model, PageNames.PHOTO_LIST);
        return "skin:/photo-list";
    }

    @RequestMapping("/photo/view")
    public String photoView(@RequestParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response, Model model) {
        Photo photo = photoService.getPhotoById(id);
        Album album = albumService.getAlbumById(photo.getAlbum().getId());
        photo.setAlbum(album);

        model.addAttribute(FreemarkerVariableNames.PHOTO, photo);
        model.addAttribute(FreemarkerVariableNames.ALBUM, album);
        setCurrnetPage(model, PageNames.PHOTO_VIEW);
        return "skin:/photo-view";
    }

}
