package com.xw.project.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 青少年会员
 * </p>
 *
 * @author dy
 * @since 2020-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrgMemberYoung extends Model<OrgMemberYoung> {

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
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标识
     */
    private String delFlag;


    @Override
    protected Serializable pkVal() {
        return this.memberId;
    }

}
