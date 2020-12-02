package com.xw.project.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xw.common.entity.BasePlusEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jy
 * @since 2020-06-23
 */
@TableName("rtm_aed")
@Data
public class RtmAed extends BasePlusEntity {

private static final long serialVersionUID=1L;

    private String id;

    /**
     * 安装时间
     */
    private Date installDate;

    /**
     * 品牌
     */
    private String model;


}
