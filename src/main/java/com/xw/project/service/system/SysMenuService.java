package com.xw.project.service.system;

import com.xw.project.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author dy
 * @since 2019-12-20
 */
public interface SysMenuService extends IService<SysMenu> {
    List<SysMenu> listMenu(SysMenu sysMenu);
}
