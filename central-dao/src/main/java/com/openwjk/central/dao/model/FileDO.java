package com.openwjk.central.dao.model;

import java.io.Serializable;
import java.util.Date;

public class FileDO implements Serializable {
    private Long id;

    private String creator;

    private Date gmtCreate;

    private String modifier;

    private Date gmtModified;

    private String isDeleted;

    private String fileStoreBucket;

    private String fileStoreKey;

    private String originalName;

    private String contentType;

    private String md5Digest;

    private Long menuId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    public String getFileStoreBucket() {
        return fileStoreBucket;
    }

    public void setFileStoreBucket(String fileStoreBucket) {
        this.fileStoreBucket = fileStoreBucket == null ? null : fileStoreBucket.trim();
    }

    public String getFileStoreKey() {
        return fileStoreKey;
    }

    public void setFileStoreKey(String fileStoreKey) {
        this.fileStoreKey = fileStoreKey == null ? null : fileStoreKey.trim();
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName == null ? null : originalName.trim();
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType == null ? null : contentType.trim();
    }

    public String getMd5Digest() {
        return md5Digest;
    }

    public void setMd5Digest(String md5Digest) {
        this.md5Digest = md5Digest == null ? null : md5Digest.trim();
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}