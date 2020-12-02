package com.xw.project.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author dy
 * @since 2020-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    private String id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父菜单ID
     */
    private String parentId;

    /**
     * 显示顺序
     */
    private Integer sortBy;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 菜单类型（1首页菜单 2右侧菜单 3统计分析 菜单类型（M目录 C菜单 F按钮））
     */
    private String menuType;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    private String visible;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 是否含有子级 0无 1有
     */
    private String childrenFlag;

    /**
     * 菜单层级
     */
    private Boolean level;

    /**
     * 删除标记 1删除 0正常
     */
    private String delFlag;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 0否    1是
     */
    private String isHidden;

    /**
     * 平台标识 统一申办（XC）其它自行添加
     */
    private String platform;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
