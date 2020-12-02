package com.xw.project.rescuetraing.resourcemanage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xw.common.entity.BasePlusEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jy
 * @since 2020-06-12
 */
@TableName("rescue_res_aedmanage")
public class RescueResAedmanage extends BasePlusEntity {

private static final long serialVersionUID=1L;

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final String NAME = "name";

    @Override
    public String toString() {
        return "RescueResAedmanage{" +
        "name=" + name +
        "}";
    }
}
