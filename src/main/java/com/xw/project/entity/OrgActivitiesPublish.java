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
 * @author dy
 * @since 2020-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrgActivitiesPublish extends Model<OrgActivitiesPublish> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String activityName;

    private String activityAddress;

    private String activityContent;

    private String orgId;

    private Date startTime;

    private Date endTime;

    private Date updateDate;

    private String updateBy;

    private Date createDate;

    private String createBy;

    private String delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
