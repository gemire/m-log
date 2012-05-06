package org.mspring.mlog.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Upload entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "upload")
public class Upload implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = 6769168441147731786L;

    private Integer id;
    private Integer fileSize;
    private String fileName;
    private Date postTime;
    private String quote;
    private Integer downNum;

    // Constructors

    /** default constructor */
    public Upload() {
    }

    /** full constructor */
    public Upload(Integer fileSize, String fileName, Date postTime, String quote, Integer downNum) {
        this.fileSize = fileSize;
        this.fileName = fileName;
        this.postTime = postTime;
        this.quote = quote;
        this.downNum = downNum;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "fileSize_")
    public Integer getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    @Column(name = "fileName_", unique = true, length = 50)
    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "postTime_", length = 19)
    public Date getPostTime() {
        return this.postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    @Column(name = "quote_")
    public String getQuote() {
        return this.quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Column(name = "downNum_")
    public Integer getDownNum() {
        return this.downNum;
    }

    public void setDownNum(Integer downNum) {
        this.downNum = downNum;
    }

}