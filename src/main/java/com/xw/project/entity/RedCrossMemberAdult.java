package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.xw.common.entity.BasePlusEntity;
import java.util.Date;

/**
 * <p>
 * adult_member 成人会员表
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-06
 */
@TableName("red_cross_member_adult")
public class RedCrossMemberAdult extends BasePlusEntity {

private static final long serialVersionUID=1L;

    /**
     * 会员标识
     */
    private String memberId;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 性别（1男0女）
     */
    private String sex;

    /**
     * 文化程度(数据字典)
     */
    private String educationDegree;

    /**
     * 出生年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthDate;

    /**
     * 民族（数据字典）
     */
    private String nation;

    /**
     * 职业（数据字典）
     */
    private String profession;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 工作单位
     */
    private String workUnit;

    /**
     * 住址
     */
    private String address;




    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEducationDegree() {
        return educationDegree;
    }

    public void setEducationDegree(String educationDegree) {
        this.educationDegree = educationDegree;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public static final String MEMBER_ID = "member_id";

    public static final String ID_CARD = "id_card";

    public static final String SEX = "sex";

    public static final String EDUCATION_DEGREE = "education_degree";

    public static final String BIRTH_DATE = "birth_date";

    public static final String NATION = "nation";

    public static final String PROFESSION = "profession";

    public static final String CONTACT_PHONE = "contact_phone";

    public static final String WORK_UNIT = "work_unit";

    public static final String ADDRESS = "address";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    public String toString() {
        return "RedCrossMemberAdult{" +
        "memberId=" + memberId +
        ", idCard=" + idCard +
        ", sex=" + sex +
        ", educationDegree=" + educationDegree +
        ", birthDate=" + birthDate +
        ", nation=" + nation +
        ", profession=" + profession +
        ", contactPhone=" + contactPhone +
        ", workUnit=" + workUnit +
        ", address=" + address +
        "}";
    }
}
