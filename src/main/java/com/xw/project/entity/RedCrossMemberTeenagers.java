package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.xw.common.entity.BasePlusEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 青少年会员
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-06
 */
@TableName("red_cross_member_teenagers")
@Data
@EqualsAndHashCode(callSuper = false)
public class RedCrossMemberTeenagers extends BasePlusEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 会员标识
     */
    private String memberId;

    /**
     * 学校(可以算作地址)
     */
    private String schoolName;

    /**
     * 系科
     */
    private String faculty;

    /**
     * 班级
     */
    private String className;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createDate;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date upDateDate;
}



