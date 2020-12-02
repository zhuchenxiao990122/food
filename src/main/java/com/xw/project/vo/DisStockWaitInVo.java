package com.xw.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能描述：
 *
 * @Author: 喻礼
 * @Date: 2020/3/30 17:45
 */
public class DisStockWaitInVo {

    private String id;

    private String warehouseId;

    /**
     * 协调id
     */
    private String coordinateId;

    /**
     * 通知单号
     */
    private String noticeCode;

    private BigDecimal total;

    /**
     * 经手人
     */
    private String handler;

    /**
     * 批准人
     */
    private String ratifier;

    /**
     * 核准人
     */
    private String approver;

    /**
     * 状态0未入库1已入库
     */
    private String status;


    private Date publishTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getCoordinateId() {
        return coordinateId;
    }

    public void setCoordinateId(String coordinateId) {
        this.coordinateId = coordinateId;
    }

    public String getNoticeCode() {
        return noticeCode;
    }

    public void setNoticeCode(String noticeCode) {
        this.noticeCode = noticeCode;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getRatifier() {
        return ratifier;
    }

    public void setRatifier(String ratifier) {
        this.ratifier = ratifier;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}
