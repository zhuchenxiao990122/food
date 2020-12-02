package com.xw.project.vo;

import com.xw.project.entity.DisRescueRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class DisRescueRequestVo  {

    /**
     * 发布id
     */
    @NotEmpty
    private String id;

    /**
     * 发布id()
     */
    @NotEmpty
    private String publishId;
    /**
     * 发布单位
     */
    private String publishOrg;

    /**
     * 上报编号
     */
    @NotEmpty
    private String publishCode;

    /**
     * 发布单位名称
     */
    private String publishName;

    /**
     * 接收单位名称
     */
    private String receiveName;

    /**
     * 灾害类型编号
     */
    private String disasterType;

    /**
     * 灾害名称
     */
    private String disasterName;

    /**
     * 合计金额
     */
    private String rescueTotal;

    /**
     * 物资申请次数
     */
    private String applyNumber;

    /**
     * 物资申请
     */
    private String applyName;

    /**
     * 物资
     */
    private List<DisRescueRequest> disRescueRequests;

}
