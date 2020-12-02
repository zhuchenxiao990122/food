package com.xw.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class DisMaterialWarehouseVo {

    private String warehouseId;
    private String organizeId;
    private String organizeName;
    private String warehouseType;
    private String warehouseTypeName;
    private String code;

    private String name;
    private String area;
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date establishDate;

    private String linkPerson;
    private String phone;
    private String remarks;
}
