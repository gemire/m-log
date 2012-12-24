/**
 * 
 */
package org.mspring.mlog.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

/**
 * @author Gao Youbo
 * @since 2012-10-15
 * @Description
 * @TODO
 */
@Entity
@Table(name = "photo")
public class Photo implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1776070953172107621L;

    @Expose
    private Long id;
    @Expose
    private String name;
    private Album album;
    @Expose
    private Integer width;
    @Expose
    private Integer height;
    @Expose
    private String fileName;
    @Expose
    private String url;
    @Expose
    private String previewUrl;
    private String previewFileName;
    private Short photoYear;
    private Short photoMonth;
    private Short photoDate;
    private int size;
    private Integer colorBit;
    private Date createTime;
    private Date modifyTime;
    private String description;

    // 以下是JPG图片的EXIF信息
    protected String manufacturer; // 厂商:EASTMAN KODAK COMPANY
    protected String model; // 型号:KODAK DX7590 ZOOM DIGITAL CAMERA
    protected int ISO = -1; // ISO: 80
    protected String aperture; // 光圈:F3.2
    protected String shutter; // 快门速度: 1/29 sec
    protected String exposureBias; // 曝光补偿: 0 EV
    protected String exposureTime; // 曝光时间: 1/30 sec
    protected String focalLength; // 焦距: 26.4 mm
    protected String colorSpace; // 色彩空间: sRGB

    // private String exifManufacturer;
    // private String exifModel;
    // private Integer exifIso;
    // private String exifAperture;
    // private String exifShutter;
    // private String exifExposureBias;
    // private String exifExposureTime;
    // private String exifFocalLength;
    // private String exifColorSpace;

    private int orientation;// 方向

    /**
     * 
     */
    public Photo() {
        super();
    }

    /**
     * @param id
     */
    public Photo(Long id) {
        super();
        this.id = id;
    }

    @Transient
    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "photo_name", length = 1000)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = true, targetEntity = Album.class)
    @JoinColumn(name = "album_id")
    public Album getAlbum() {
        return this.album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Column(name = "photo_width")
    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Column(name = "photo_height")
    public Integer getHeight() {
        return this.width;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Column(name = "file_name", length = 200)
    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name = "photo_url", length = 500)
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "preview_url", length = 500)
    public String getPreviewUrl() {
        return this.previewUrl;
    }

    @Column(name = "preview_file_name", length = 200)
    public String getPreviewFileName() {
        return previewFileName;
    }

    public void setPreviewFileName(String previewFileName) {
        this.previewFileName = previewFileName;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    @Column(name = "photo_year")
    public Short getPhotoYear() {
        return this.photoYear;
    }

    public void setPhotoYear(Short photoYear) {
        this.photoYear = photoYear;
    }

    @Column(name = "photo_month")
    public Short getPhotoMonth() {
        return this.photoMonth;
    }

    public void setPhotoMonth(Short photoMonth) {
        this.photoMonth = photoMonth;
    }

    @Column(name = "photo_date")
    public Short getPhotoDate() {
        return this.photoDate;
    }

    public void setPhotoDate(Short photoDate) {
        this.photoDate = photoDate;
    }

    @Column(name = "photo_size")
    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Column(name = "color_bit")
    public Integer getColorBit() {
        return this.colorBit;
    }

    public void setColorBit(Integer colorBit) {
        this.colorBit = colorBit;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", length = 30)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_time", length = 30)
    public Date getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Column(name = "description", columnDefinition = "text")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "exif_manufacturer", length = 50)
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Column(name = "exif_model", length = 50)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "exif_iso")
    public int getISO() {
        return ISO;
    }

    public void setISO(int iso) {
        ISO = iso;
    }

    @Column(name = "exif_aperture", length = 20)
    public String getAperture() {
        return aperture;
    }

    public void setAperture(String aperture) {
        this.aperture = aperture;
    }

    @Column(name = "exif_shutter", length = 20)
    public String getShutter() {
        return shutter;
    }

    public void setShutter(String shutter) {
        this.shutter = shutter;
    }

    @Column(name = "exif_exposure_bias", length = 20)
    public String getExposureBias() {
        return exposureBias;
    }

    public void setExposureBias(String exposureBias) {
        this.exposureBias = exposureBias;
    }

    @Column(name = "exif_exposure_time", length = 20)
    public String getExposureTime() {
        return exposureTime;
    }

    public void setExposureTime(String exposureTime) {
        this.exposureTime = exposureTime;
    }

    @Column(name = "exif_focal_length", length = 20)
    public String getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(String focalLength) {
        this.focalLength = focalLength;
    }

    @Column(name = "exif_color_space", length = 20)
    public String getColorSpace() {
        return colorSpace;
    }

    public void setColorSpace(String colorSpace) {
        this.colorSpace = colorSpace;
    }

}