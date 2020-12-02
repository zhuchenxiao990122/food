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
 * @since 2020-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DisContact extends Model<DisContact> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 1.捐赠个人2.捐赠单位组织3.生产厂家4.协调单位
     */
    private String type;

    /**
     * 唯一标识，身份证、护照、营业执照号等
     */
    private String identification;

    /**
     * 名称
     */
    private String name;

    /**
     * 国籍
     */
    private String nation;

    /**
     * 境内境外
     */
    private String area;

    /**
     * 地址
     */
    private String address;

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
