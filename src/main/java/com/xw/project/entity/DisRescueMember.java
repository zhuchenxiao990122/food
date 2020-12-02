package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xw.common.entity.BasePlusEntity;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author weiLiang
 * @since 2020-06-19
 */
@Data
@TableName("dis_rescue_member")
public class DisRescueMember extends BasePlusEntity {

    private static final long serialVersionUID = 1L;

        /**
     * 队伍人员 Id
     */
    private String id;

    /**
     * 队伍人员名称
     */
    private String memberName;

    /**
     * 队伍人员性别
     */
    private String sex;

    /**
     * 出生年月
     */
    private Date birthday;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 队伍人员职务
     */
    private String duty;

    /**
     * 队伍人员电话
     */
    private String phone;

    /**
     * 队伍人员入队时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date joinDate;

    /**
     * 队伍人员联系地址
     */
    private String address;

    /**
     * 备注
     */
    private String remarks;

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static final String TEAM_ID = "team_id";

    public static final String ID_CARD = "id_card";

    public static final String MEMBER_NAME = "member_name";

    public static final String SEX = "sex";

    public static final String BIRTHDAY = "birthday";

    public static final String DUTY = "duty";

    public static final String PHONE = "phone";

    public static final String JOIN_DATE = "join_date";

    public static final String ADDRESS = "address";

    public static final String REMARKS = "remarks";
}
