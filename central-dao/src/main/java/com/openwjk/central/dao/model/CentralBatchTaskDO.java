package com.openwjk.central.dao.model;

import java.io.Serializable;
import java.util.Date;

public class CentralBatchTaskDO implements Serializable {
    private Long id;

    private String creator;

    private Date gmtCreate;

    private String modifier;

    private Date gmtModified;

    private String isDeleted;

    private String taskType;

    private String dateLabel;

    private String status;

    private Date earlyStartTime;

    private Date startTime;

    private Date endTime;

    private String resultExt;

    private Integer retryTime;

    private Long deletePk;

    private String paramExt;

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

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType == null ? null : taskType.trim();
    }

    public String getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(String dateLabel) {
        this.dateLabel = dateLabel == null ? null : dateLabel.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getEarlyStartTime() {
        return earlyStartTime;
    }

    public void setEarlyStartTime(Date earlyStartTime) {
        this.earlyStartTime = earlyStartTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getResultExt() {
        return resultExt;
    }

    public void setResultExt(String resultExt) {
        this.resultExt = resultExt == null ? null : resultExt.trim();
    }

    public Integer getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(Integer retryTime) {
        this.retryTime = retryTime;
    }

    public Long getDeletePk() {
        return deletePk;
    }

    public void setDeletePk(Long deletePk) {
        this.deletePk = deletePk;
    }

    public String getParamExt() {
        return paramExt;
    }

    public void setParamExt(String paramExt) {
        this.paramExt = paramExt == null ? null : paramExt.trim();
    }
}