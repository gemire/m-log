/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.Album;
import org.mspring.mlog.entity.Photo;
import org.mspring.mlog.service.AlbumService;
import org.mspring.mlog.service.PhotoService;
import org.mspring.mlog.support.resolver.QueryParam;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.admin.query.PhotoQueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Gao Youbo
 * @since 2012-10-17
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin/photo")
public class PhotoWidget extends AbstractAdminWidget {
    @Autowired
    private PhotoService photoService;

    @Autowired
    private AlbumService albumService;

    @RequestMapping("/upload")
    public String photoUploadView(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Album> albums = albumService.findAllAlbum();
        model.addAttribute("albums", albums);
        return "/admin/photo/upload";
    }

    @RequestMapping("/doUpload")
    @ResponseBody
    public String doUpload(@RequestParam(required = false) Long album, HttpServletRequest request, HttpServletResponse response) {
        // JSONObject res = new JSONObject();
        if (album == null) {
            // res.put("success", false);
            // return res.toString();
            return "false";
        }
        try {
            Photo photo = photoService.createPhoto(request, album);
            // res.put("success", true);
            // res.put("preview", photo.getPreviewUrl());
            // res.put("url", photo.getUrl());
            return photo.getUrl();
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            // res.put("success", false);
            return "false";
        }
    }

    @RequestMapping({ "/list", "/", "" })
    public String listPost(@ModelAttribute Page<Photo> photoPage, @ModelAttribute Photo photo, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Album> albums = albumService.findAllAlbum();
        Album album = null;
        if (photo == null || photo.getAlbum() == null || photo.getAlbum().getId() == null) {
            album = albums.get(0);
            photo.setAlbum(album);
            if (queryParams == null) {
                queryParams = new HashMap();
            }
            queryParams.put("album.id", album.getId());
            // return prompt(model, "请先选择相册");
        }
        else {
            album = albumService.getAlbumById(photo.getAlbum().getId());
        }

        if (photoPage == null) {
            photoPage = new Page<Photo>();
        }
        photoPage.setSort(new Sort("id", Sort.DESC));
        photoPage.setPageSize(21);
        photoPage = photoService.findPhoto(photoPage, new PhotoQueryCriterion(queryParams));

        model.addAttribute("album", album);
        model.addAttribute("albums", albums);
        model.addAttribute("photoPage", photoPage);
        return "/admin/photo/listPhoto";
    }

    @RequestMapping("/delete")
    public String deletePhoto(@RequestParam(required = false) Long[] id, @ModelAttribute Page<Photo> photoPage, @ModelAttribute Photo photo, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        photoService.deletePhoto(id);
        return listPost(photoPage, photo, queryParams, request, response, model);
    }

    @RequestMapping("/edit")
    public String toEditPhoto(@RequestParam(required = false) Long id, @ModelAttribute Photo photo, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            return prompt(model, "请先选择一张照片");
        }
        photo = photoService.getPhotoById(id);
        Album album = albumService.getAlbumById(photo.getAlbum().getId());
        photo.setAlbum(album);
        model.addAttribute("photo", photo);
        model.addAttribute("album", album);
        return "/admin/photo/editPhoto";
    }

    @RequestMapping("/doEdit")
    public String doEditPhoto(@ModelAttribute Photo photo, HttpServletRequest request, HttpServletResponse response, Model model) {
        photoService.updatePhoto(photo);
        return "redirect:/admin/photo/edit?id=" + photo.getId();
    }

    @RequestMapping("/cover")
    @ResponseBody
    public String setCover(@RequestParam(required = false) Long albumId, @RequestParam(required = false) Long photoId, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (albumId == null || photoId == null) {
            return "false";
        }
        try {
            albumService.setCover(albumId, photoId);
        }
        catch (Exception e) {
            // TODO: handle exception
            return "false";
        }
        return "true";
    }

}
