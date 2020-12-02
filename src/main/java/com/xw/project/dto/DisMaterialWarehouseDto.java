package com.xw.project.dto;

import com.xw.project.util.PageHelper;
import lombok.Data;

/**
 * 救灾救援-仓库管理-仓库信息-数据传输对象
 * @author weiLiang
 * @since 2020-07-13
 */
@Data
public class DisMaterialWarehouseDto extends PageHelper {

    private String organizeId;
    private String warehouseType;
    private String name;
    private String code;
    private String linkPerson;
    private String address;
    private String[] establishDate;
}
