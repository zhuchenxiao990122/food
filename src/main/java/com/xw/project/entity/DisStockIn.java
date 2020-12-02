package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xw.common.entity.BasePlusEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuli
 * @since 2020-06-19
 */
@TableName("dis_stock_in")
public class DisStockIn extends BasePlusEntity {

private static final long serialVersionUID=1L;

    /**
     * 入库编号
     */
    private String code;

    /**
     * 通知id
     */
    private String noticeId;

    /**
     * 所属仓库组织id
     */
    private String warehouseId;

    /**
     * 经手人
     */
    private String handler;

    /**
     * 核准人
     */
    private String approver;

    private String remarks;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public static final String CODE = "code";

    public static final String NOTICE_ID = "notice_id";

    public static final String WAREHOUSE_ID = "warehouse_id";

    public static final String HANDLER = "handler";

    public static final String APPROVER = "approver";

    public static final String REMARKS = "remarks";

    @Override
    public String toString() {
        return "DisStockIn{" +
        "code=" + code +
        ", noticeId=" + noticeId +
        ", warehouseId=" + warehouseId +
        ", handler=" + handler +
        ", approver=" + approver +
        ", remarks=" + remarks +
        "}";
    }
}
