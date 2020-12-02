package com.xw.project.service.organize.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.xw.core.enums.DelFlagEnum;
import com.xw.project.entity.OrgActivitiesPublish;
import com.xw.project.mapper.OrgActivitiesPublishMapper;
import com.xw.project.service.organize.OrgActivitiesPublishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dy
 * @since 2020-01-06
 */
@Service
public class OrgActivitiesPublishServiceImpl extends ServiceImpl<OrgActivitiesPublishMapper, OrgActivitiesPublish> implements OrgActivitiesPublishService {
    @Override
    public IPage<OrgActivitiesPublish> listActivitiesForPage() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        defaultTokenServices.loadAuthentication();
        QueryWrapper<OrgActivitiesPublish> queryWrapper = new QueryWrapper<>();
        //未删除
        queryWrapper.eq("del_flag", DelFlagEnum.NOT_DEL.getDelFlag());
        IPage<OrgActivitiesPublish> orgActivitiesPublishIPag = new Page<>(1,2);
        IPage<OrgActivitiesPublish> list = this.page(orgActivitiesPublishIPag,queryWrapper);
        List<OrgActivitiesPublish> resultList = orgActivitiesPublishIPag.getRecords();
        return list;
    }

    @Override
    public OrgActivitiesPublish getActivity(String id) {
        return this.getById(id);
    }

    @Override
    public boolean saveActivity(OrgActivitiesPublish orgActivitiesPublish) {
        boolean save = this.save(orgActivitiesPublish);
        return save;
    }

    @Override
    public boolean removeActivity(String id) {
        OrgActivitiesPublish orgActivitiesPublish = new OrgActivitiesPublish();
        orgActivitiesPublish.setId(id);
        orgActivitiesPublish.setDelFlag(DelFlagEnum.IS_DEL.getDelFlag());
        //活动信息假删除
        boolean b = this.updateById(orgActivitiesPublish);
        return b;
    }

    @Override
    public boolean editActivity(OrgActivitiesPublish orgActivitiesPublish) {
        boolean b = this.updateById(orgActivitiesPublish);
        return b;
    }
}
