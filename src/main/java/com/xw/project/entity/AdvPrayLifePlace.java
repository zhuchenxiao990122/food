package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.util.Date;

import com.xw.common.entity.BasePlusEntity;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jy
 * @since 2020-07-06
 */
@Data
@TableName("adv_pray_life_place")
public class AdvPrayLifePlace extends BasePlusEntity {

private static final long serialVersionUID=1L;

    /**
     * 安装时间
     */
    private Date installDate;

    public static final String INSTALL_DATE = "install_date";

    @Override
    public String toString() {
        return "AdvPrayLifePlace{" +
        "installDate=" + installDate +
        "}";
    }
}
