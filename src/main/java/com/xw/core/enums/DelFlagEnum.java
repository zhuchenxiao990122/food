package com.xw.core.enums;

import lombok.Getter;

/**
 * 是否删除枚举类
 * @author dy
 * @since 2020/01/09
 */
public enum DelFlagEnum {
    NOT_DEL("未删除", "0"),
    IS_DEL("已删除", "1");

    @Getter
    private String theRemark;

    @Getter
    private String delFlag;

    DelFlagEnum(String theRemark, String delFlag) {
        this.theRemark = theRemark;
        this.delFlag = delFlag;
    }
}
