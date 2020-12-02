package com.xw.project.controller.system;


import com.xw.project.entity.SysMenu;
import com.xw.project.service.system.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author dy
 * @since 2019-12-20
 */
@RestController
@RequestMapping("/sysMenu")
@Api(value = "菜单权限")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping
    @ApiOperation("获取一级菜单列表")
    public Object listMenu(SysMenu sysMenu){
        try {
            return sysMenuService.listMenu(sysMenu);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
