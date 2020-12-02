package com.xw.project.vo;

import com.xw.project.entity.DisDisasterPublish;
import com.xw.project.entity.DisRescueRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class DisPublishVo extends DisDisasterPublish {

    /**
     * 发布单位
     */
    private String publishName;

    /**
     * 接收单位
     */
    private String receiveName;

    /**
     * 灾害类型
     */
    private String disasterName;

    /**
     * 救助物品总计
     */
    private BigDecimal rescueTotal;

    /**
     * 物资
     */
    private List<DisRescueRequest> disRescueRequests;
}
