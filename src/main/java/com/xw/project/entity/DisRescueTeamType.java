package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xw.common.entity.BasePlusEntity;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author weiLiang
 * @since 2020-06-18
 */
@Data
@TableName("sys_dict")
public class DisRescueTeamType extends BasePlusEntity {
    private String id;
    private String value;
    private String name;
    private String type;
    private String description;
}
