package com.xw.project.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuli
 * @since 2020-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ComDonorsDetail extends Model<ComDonorsDetail>  {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 联系id
     */
    private String donorId;

    /**
     * 手机
     */
    private String phone;

    /**
     * 联系人
     */
    private String linkPerson;

    private String email;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 最后更新时间
     */
    private Date updateDate;

    private Date createDate;

    private String updateBy;

    private String createBy;

    /**
     * 0正常，1删除
     */
    private String delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
