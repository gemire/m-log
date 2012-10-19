/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.mspring.mlog.entity.Album;
import org.mspring.mlog.entity.Photo;
import org.mspring.mlog.service.AlbumService;
import org.mspring.mlog.service.PhotoService;
import org.mspring.platform.web.widget.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
public class PhotoWidget {
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
        JSONObject res = new JSONObject();
        if (album == null) {
            res.put("success", false);
            return res.toString();
        }
        try {
            Photo photo = photoService.createPhoto(request, album);
            res.put("success", true);
            res.put("preview", photo.getPreviewUrl());
            res.put("url", photo.getUrl());
        }
        catch (Exception e) {
            // TODO: handle exception
            res.put("success", false);
        }
        return res.toString();
    }
}
