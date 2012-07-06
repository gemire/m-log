/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import org.mspring.mlog.util.ImageUtils;
import org.mspring.mlog.util.MLogUtils;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
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
     * @see org.mspring.mlog.service.PhotoService#createPhoto(javax.servlet.http.
     *      HttpServletRequest, java.lang.String)
     */
    @Override
    public void createPhoto(HttpServletRequest request, String path, Long album) throws IOException {
        // TODO Auto-generated method stub
        createPhoto(request, path, album, DEFAULT_AUTO_ROTATE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PhotoService#createPhoto(javax.servlet.http.
     *      HttpServletRequest, java.lang.String)
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

                BufferedImage sourceImage = ImageIO.read(originalImage);

                Photo photo = new Photo();

                // 限制图片最大大小
                boolean isLimit = "1".equals(optionService.getOption(ConfigTokens.mspring_album_islimit_size));
                if (isLimit) {
                    String string_ori_width = optionService.getOption(ConfigTokens.mspring_album_islimit_width);
                    String string_ori_height = optionService.getOption(ConfigTokens.mspring_album_islimit_height);
                    int MAX_WIDTH = StringUtils.isBlank(string_ori_width) ? DEFAULT_MAX_WIDTH : Integer.parseInt(string_ori_width);
                    int MAX_HEIGHT = StringUtils.isBlank(string_ori_height) ? DEFAULT_MAX_HEIGHT : Integer.parseInt(string_ori_height);

                    // 图片大小超过限定范围，调整图片大小
                    if (sourceImage.getWidth() > MAX_WIDTH && sourceImage.getHeight() > MAX_HEIGHT) {
                        // targetImage = ImageUtils.resize(sourceImage,
                        // MAX_WIDTH, MAX_HEIGHT);
                        ImageUtils.saveImage(originalImage, originalImage, MAX_WIDTH, MAX_HEIGHT);

                        // 重新读取图片
                        sourceImage = ImageIO.read(originalImage);
                    }
                }
                photo.setWidth(sourceImage.getWidth());
                photo.setHeight(sourceImage.getHeight());
                photo.setAlbum(new Album(album));
                photo.setFileName(originalImage.getName());
                photo.setName(originalImage.getName().substring(0, originalImage.getName().lastIndexOf(".")));
                photo.setUrl(imageAbstractUrl);
                photo.setPreviewUrl(previewImageAbstractUrl);

                // 创建缩略图
                ImageUtils.saveImage(originalImage, new File(previewImagePath), PREVIEW_WIDTH, PREVIEW_HEIGHT);
                // 图片信息插入数据库
                insertPhotoToDB(photo, originalImagePath, autoRotate);
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
     * @see org.mspring.mlog.service.PhotoService#deletePhoto(java.lang.Long[])
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
        deletePhotoFile(basePath, imagePath, previewPath);
        photoDao.delete(photo);
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
     * @see org.mspring.mlog.service.PhotoService#findPhotoById(java.lang.Long)
     */
    @Override
    public Photo findPhotoById(Long id) {
        // TODO Auto-generated method stub
        return photoDao.get(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PhotoService#queryPhoto(org.mspring.platform.dao.support.Page,
     *      org.mspring.platform.dao.query.QueryCriterion)
     */
    @Override
    public Page<Photo> queryPhoto(Page<Photo> page, QueryCriterion queryCriterion) {
        // TODO Auto-generated method stub
        return photoDao.findPage(page, queryCriterion);
    }

    /*
     * (non-Javadoc)
     * 
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

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PhotoService#findPhotosByAlbum(java.lang.Long)
     */
    @Override
    public List<Photo> findPhotosByAlbum(Long albumId) {
        // TODO Auto-generated method stub
        return photoDao.find(" select photo from Photo photo where photo.album.id = ? ", albumId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PhotoService#findNearPhotos(java.lang.Long,
     *      java.lang.Long, int)
     */
    @Override
    public List<Photo> findNearPhotos(Long albumId, Long currentPhotoId, int length) {
        // TODO Auto-generated method stub
        List<Photo> result = new ArrayList<Photo>();
        List<Photo> allPhotosInAlbum = findPhotosByAlbum(albumId);
        Photo currentPhoto = findPhotoById(currentPhotoId);
        if (currentPhoto != null) {
            int currentPoint = allPhotosInAlbum.indexOf(currentPhoto);
            if (currentPoint > 0) {
                int startIndex, endIndex;
                if ((length / 2) >= currentPoint) {
                    startIndex = 0;
                    endIndex = length;
                } else {
                    startIndex = currentPoint - (length / 2);
                    endIndex = currentPoint + (length / 2) + 1;
                    if (endIndex > (allPhotosInAlbum.size() - 1)) {
                        startIndex = allPhotosInAlbum.size() - length;
                        endIndex = allPhotosInAlbum.size();
                    }
                }
                result = allPhotosInAlbum.subList(startIndex, endIndex);
            }
        }
        return result;
    }
}
