package com.xw.project.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
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
public class SysExcelDef extends Model<SysExcelDef> {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库中表名
     */
    private String fileTemplate;

    /**
     * 是主题还是列表信息 0表示主题main_filed 1表示列表 2表示起始位置
     */
    private String fieldType;

    /**
     * 字段定义
     */
    private String fieldName;

    /**
     * 行
     */
    private Integer row;

    /**
     * 列
     */
    private Integer col;


    @Override
    protected Serializable pkVal() {
        return this.fileTemplate;
    }

}
