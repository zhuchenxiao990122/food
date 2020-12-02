package com.xw.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class AEDManageVo {
    @ApiModelProperty(notes = "主键")
    private String id;

    //地址ID
    @ApiModelProperty("名称")
    private String name;

     //地址名称
    @ApiModelProperty("编码")
    private String code;

    @ApiModelProperty("上级ID")
    private String parentId;

    //AED编号
    @ApiModelProperty("aed编号")
    private String aedId;

    //安装地点
    @ApiModelProperty("安装地点")
    private String installAddress;

    //经度
    @ApiModelProperty("经度")
    private Double positionLon;

    //纬度
    @ApiModelProperty("纬度")
    private Double positionLat;

    //异常标志
    @ApiModelProperty("异常标志")
    private String errorFlag;

    @ApiModelProperty("所有的地址信息")
    private  String allAddressMessage;

    private String delFlag;

    private List<AEDManageVo> childrens;

}
