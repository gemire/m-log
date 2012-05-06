/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.mspring.mlog.common.ConfigTokens;
import org.mspring.mlog.dao.PhotoDao;
import org.mspring.mlog.entity.Album;
import org.mspring.mlog.entity.Photo;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.PhotoService;
import org.mspring.mlog.util.MLogUtils;
import org.mspring.mlog.util.ImageUtils;
import org.mspring.platform.dao.query.AbstractQueryCriterion;
import org.mspring.platform.dao.query.QueryCriterion;
import org.mspring.platform.dao.support.Page;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since Dec 29, 2011
 */
@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

    private static final Logger log = Logger.getLogger(PhotoServiceImpl.class);
    private static final boolean DEFAULT_AUTO_ROTATE = false;

    @Value("${upload.folder}")
    private String uploadFolder;
    @Value("${upload.preview.folder}")
    private String previewFolder;

    private PhotoDao photoDao;
    private OptionService optionService;

    @Autowired
    public void setPhotoDao(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PhotoService#createPhoto(javax.servlet.http.
     * HttpServletRequest, java.lang.String)
     */
    @Override
    public void createPhoto(HttpServletRequest request, String path, Long album) throws IOException {
        // TODO Auto-generated method stub
        createPhoto(request, path, album, DEFAULT_AUTO_ROTATE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PhotoService#createPhoto(javax.servlet.http.
     * HttpServletRequest, java.lang.String)
     */
    @Override
    public void createPhoto(HttpServletRequest request, String path, Long album, boolean autoRotate) throws IOException {
        // TODO Auto-generated method stub
        DiskFileItemFactory fac = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fac);
        upload.setHeaderEncoding("utf-8");
        List<FileItem> fileList = null;
        try {
            fileList = upload.parseRequest(request);
        } catch (FileUploadException e) {
            // TODO: handle exception
            return;
        }
        Iterator<FileItem> it = fileList.iterator();
        String fileName = ""; // 文件名
        String extendName = ""; // 文件后缀
        String imageAbstractUrl = ""; // 图片相对路径
        String previewImageAbstractUrl = ""; // 预览图相对路径
        String originalImagePath = ""; // 文件完整路径,包括名称
        String previewImagePath = ""; // 缩略图完整路径
        File originalImage;
        while (it.hasNext()) {
            FileItem item = it.next();
            if (!item.isFormField()) {
                fileName = item.getName();
                if (StringUtils.isBlank(fileName)) {
                    continue;
                }

                extendName = StringUtils.getFileExtend(fileName);
                imageAbstractUrl = MLogUtils.getAbstractImageUrl(uploadFolder, extendName);
                previewImageAbstractUrl = MLogUtils.getAbstractPreviewImageUrl(imageAbstractUrl, previewFolder);
                originalImagePath = path + imageAbstractUrl;
                previewImagePath = path + previewImageAbstractUrl;

                originalImage = new File(originalImagePath);
                if (!originalImage.getParentFile().exists()) {
                    originalImage.getParentFile().mkdirs();
                }
                try {
                    item.write(originalImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                log.debug("uploading image " + originalImagePath);

                BufferedImage oldImage = ImageIO.read(originalImage);
                Photo photo = new Photo();
                photo.setAlbum(new Album(album));
                photo.setFileName(originalImage.getName());
                photo.setName(originalImage.getName().substring(0, originalImage.getName().lastIndexOf(".")));
                photo.setUrl(imageAbstractUrl);
                photo.setPreviewUrl(previewImageAbstractUrl);
                photo.setWidth(oldImage.getWidth());
                photo.setHeight(oldImage.getHeight());
                createPreviewImage(photo, originalImagePath, previewImagePath);
                insertPhotoToDB(photo, originalImagePath, autoRotate);
            }
        }
    }

    /**
     * 生成预览图片
     * 
     * @param photo
     *            图片对象
     * @param originalImage
     *            原始图片文件路径
     * @param oldImage
     *            原始图片
     * @throws IOException
     */
    private void createPreviewImage(Photo photo, String originalImagePath, String previewImagePath) throws IOException {

        File originalImage = new File(originalImagePath);

        // 限制图片最大大小
        boolean isLimit = "1".equals(optionService.getOption(ConfigTokens.mspring_album_islimit_size));
        if (isLimit) {
            String string_ori_width = optionService.getOption(ConfigTokens.mspring_album_islimit_width);
            String string_ori_height = optionService.getOption(ConfigTokens.mspring_album_islimit_height);
            int MAX_WIDTH = StringUtils.isBlank(string_ori_width) ? DEFAULT_MAX_WIDTH : Integer.parseInt(string_ori_width);
            int MAX_HEIGHT = StringUtils.isBlank(string_ori_height) ? DEFAULT_MAX_HEIGHT : Integer.parseInt(string_ori_height);

            int ori_width = MAX_WIDTH;
            int ori_height = MAX_HEIGHT;

            int old_width = photo.getWidth();
            int old_height = photo.getHeight();
            boolean regenerate_img = true;
            if (old_width <= MAX_WIDTH && old_height <= MAX_HEIGHT) {
                ori_width = old_width;
                ori_height = old_height;
                regenerate_img = false;
            } else if (old_width > MAX_WIDTH && old_height > MAX_HEIGHT) {
                ori_width = MAX_WIDTH;
                ori_height = old_height * ori_width / old_width;
            } else if (old_width > MAX_WIDTH && old_height <= MAX_HEIGHT) {
                ori_width = MAX_WIDTH;
                ori_height = old_height;
            } else if (old_width <= MAX_WIDTH && old_height > MAX_HEIGHT) {
                ori_height = MAX_HEIGHT;
                ori_width = old_width * ori_height / old_height;
            }
            if (regenerate_img) {
                ImageUtils.createPreviewImage(originalImage, originalImagePath, ori_width, ori_height);
                photo.setWidth(ori_width);
                photo.setHeight(ori_height);
                photo.setSize((int) new File(originalImagePath).length());
            }
        }

        // 生成缩略图
        {
            int preview_width, preview_height;
            preview_width = Math.min(PREVIEW_WIDTH, photo.getWidth());
            if (photo.getHeight() <= PREVIEW_HEIGHT)
                preview_height = photo.getHeight();
            else {
                // 按比例对图像高度进行压缩
                preview_height = photo.getHeight() * preview_width / photo.getWidth();
            }

            if (preview_width == photo.getWidth() && preview_height == photo.getHeight()) {
                // 图像不变
                previewImagePath = originalImagePath;
            } else {
                String extendName = StringUtils.getFileExtend(originalImagePath);
                // 生成略缩图
                if (ImageUtils.isImage(extendName)) {
                    previewImagePath = ImageUtils.createPreviewImage(originalImage, previewImagePath, preview_width, preview_height);
                    log.debug("create preview image " + previewImagePath);
                }
            }
        }
    }

    /**
     * 将图片信息插入数据库
     * 
     * @param photo
     * @param originalImage
     * @param oldImage
     * @param autoRotate
     * @throws IOException
     */
    private void insertPhotoToDB(Photo photo, String originalImagePath, boolean autoRotate) throws IOException {
        String extendName = StringUtils.getFileExtend(originalImagePath);
        if (ImageUtils.isJPG(extendName)) {
            try {
                photo = ImageUtils.fillExifInfo(originalImagePath, photo);
                int orient = photo.getOrientation();
                // 旋转图片
                if (autoRotate && orient > 0 && orient <= 8) {
                    ImageUtils.rotateImage(originalImagePath, orient);
                }
            } catch (Exception e) {
                // TODO: handle exception
                log.error("Exception occur when reading EXIF of " + originalImagePath, e);
            }
        } else if (ImageUtils.isBMP(extendName)) {
            String jpgName = ImageUtils.BMP_TO_JPG(originalImagePath);
            if (jpgName != null) {
                // 删除bmp文件
                if (new File(originalImagePath).delete()) {
                    originalImagePath = jpgName;
                }
            }
        }

        // 获取图片的基本信息,例如大小尺寸像素等
        File originalImage = new File(originalImagePath);
        BufferedImage oldImage = ImageIO.read(originalImage);

        photo.setColorBit(oldImage.getColorModel().getPixelSize());
        photo.setSize((int) originalImage.length());
        photoDao.save(photo);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PhotoService#deletePhoto(java.lang.Long[])
     */
    @Override
    public void deletePhoto(String basePath, Long[] ids) {
        // TODO Auto-generated method stub
        for (int i = 0; i < ids.length; i++) {
            deletePhoto(basePath, ids[i]);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PhotoService#deletePhoto(java.lang.Long)
     */
    @Override
    public void deletePhoto(String basePath, Long id) {
        // TODO Auto-generated method stub
        Photo photo = findPhotoById(id);
        String imagePath = basePath + File.separator + photo.getUrl();
        String previewPath = basePath + File.separator + photo.getPreviewUrl();

        photoDao.delete(id);
        deletePhotoFile(basePath, imagePath, previewPath);
    }

    private void deletePhotoFile(String basePath, String imagePath, String previewPath) {
        File imageFile = new File(imagePath);
        File previewFile = new File(previewPath);
        if (imageFile.exists() && !imageFile.isDirectory()) {
            imageFile.delete();
        }
        if (previewFile.exists() && !previewFile.isDirectory()) {
            previewFile.delete();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PhotoService#findPhotoById(java.lang.Long)
     */
    @Override
    public Photo findPhotoById(Long id) {
        // TODO Auto-generated method stub
        return photoDao.get(id);
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.PhotoService#queryPhoto(org.mspring.platform.dao.support.Page, org.mspring.platform.dao.query.QueryCriterion)
     */
    @Override
    public Page<Photo> queryPhoto(Page<Photo> page, QueryCriterion queryCriterion) {
        // TODO Auto-generated method stub
        return photoDao.findPage(page, queryCriterion);
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.PhotoService#hasPhotoInAlbum(java.lang.Long)
     */
    @Override
    public boolean hasPhotoInAlbum(Long albumId) {
        // TODO Auto-generated method stub
        Long count = photoDao.count("select count(*) from Photo p where p.album.id = ?", albumId);
        if (count > 0) {
            return true;
        }
        return false;
    }

}
