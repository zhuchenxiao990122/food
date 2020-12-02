package com.xw.project.service.system.impl;

import com.xw.common.entity.User;
import com.xw.common.util.StringUtil;
import com.xw.project.service.system.SysAuthorityService;
import com.xw.system.entity.Organize;
import com.xw.system.mapper.OrganizeMapper;
import com.xw.system.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class SysAuthorityServiceImpl implements SysAuthorityService {
    @Autowired
    private AppUtil appUtil;
    @Autowired
    private OrganizeMapper organizeMapper;

    @Override
    public String authorityId() {
        User user = appUtil.getUser();
        String userOrg = user.getUserOrg();
        if (StringUtil.isEmpty(userOrg)) {
            throw new IllegalArgumentException("用户所属组织为空");
        }
        Organize organize = organizeMapper.selectById(userOrg);
        if (null == organize || StringUtil.isEmpty(organize.getParentId())) {
            throw new IllegalArgumentException("用户所属组织查询不到或父类id查找失败");
        }
        return organize.getParentId();
    }
}
