package com.xw.project.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.project.entity.SysMenu;
import com.xw.project.mapper.SysMenuMapper;
import com.xw.project.service.system.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author dy
 * @since 2019-12-20
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> listMenu(SysMenu sysMenu) {
        QueryWrapper<SysMenu> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("parent_id", sysMenu.getParentId());
        List<SysMenu> list = sysMenuMapper.selectList(objectQueryWrapper);
        return sysMenuMapper.selectList(objectQueryWrapper);
    }
}
