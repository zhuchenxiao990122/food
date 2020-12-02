package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xw.common.entity.BasePlusEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 团体会员
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-06
 */
@Data
@TableName("red_cross_member_group")
public class RedCrossMemberGroup extends BasePlusEntity {

private static final long serialVersionUID=1L;

    /**
     * 会员标识
     */
    private String memberId;

    /**
     * 单位性质
     */
    private String unitProperty;

    /**
     * 法定代表
     */
    private String legalRepresentative;

    /**
     * 单位人数
     */
    private Integer unitNumber;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 常驻地区
     */
    private String permanentArea;

    /**
     * 邮政编码
     */
    private String postCode;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 电子邮箱
     */
    private String mail;

    /**
     * 联系地址
     */
    private String address;





    public static final String MEMBER_ID = "member_id";

    public static final String UNIT_PROPERTY = "unit_property";

    public static final String LEGAL_REPRESENTATIVE = "legal_representative";

    public static final String UNIT_NUMBER = "unit_number";

    public static final String contact_PERSON = "contact_person";

    public static final String PERMANENT_AREA = "permanent_area";

    public static final String POST_CODE = "post_code";

    public static final String contact_PHONE = "contact_phone";

    public static final String MAIL = "mail";

    public static final String ADDRESS = "address";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    public String toString() {
        return "RedCrossMemberGroup{" +
        "memberId=" + memberId +
        ", unitProperty=" + unitProperty +
        ", legalRepresentative=" + legalRepresentative +
        ", unitNumber=" + unitNumber +
        ", contactPerson=" + contactPerson +
        ", permanentArea=" + permanentArea +
        ", postCode=" + postCode +
        ", contactPhone=" + contactPhone +
        ", mail=" + mail +
        ", address=" + address +
        "}";
    }
}
