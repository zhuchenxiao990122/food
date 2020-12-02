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
 * @since 2020-06-30
 */
@Data
@TableName("adv_edu_life_hall")
public class AdvEduLifeHall extends BasePlusEntity {

private static final long serialVersionUID=1L;

    /**
     * 安装时间
     */
    private Date installDate;

    /**
     * 馆名
     */
    private String hallName;




    public static final String INSTALL_DATE = "install_date";



    @Override
    public String toString() {
        return "AdvEduLifeHall{" +
        "installDate=" + installDate +
        ", hallName=" + hallName +
        "}";
    }
}
