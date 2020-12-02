package com.xw.core.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * 功能:枚举常量
 *
 *@author xkh
 *@ClassName Enum.
 *@Version V1.0.
 *@date 2019.07.08
 */
public interface SystemEnum {

    /**
     * 系统统一状态码 0-有效 1-无效
     */
    public enum SYSTEM_STATE_CODE {
        OK("0", "有效"),
        INVALID_OR_DEL("1", "无效")
        ;

        @Getter
        @Setter
        private String status;
        @Getter
        private String desc;

        SYSTEM_STATE_CODE(String status, String desc) {
            this.status = status;
            this.desc = desc;
        }

        public static SYSTEM_STATE_CODE getValue(String code) {
            if (code == null) {return null;}
            for (SYSTEM_STATE_CODE item : SYSTEM_STATE_CODE.values()) {
                if (item.getDesc().equals(code)) {
                    return item;
                }
            }
            return null;
        }
    }


}
