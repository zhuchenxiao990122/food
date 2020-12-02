package com.xw.project.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.project.dto.TargetPositionDto;
import com.xw.project.mapper.TargetPositionMapper;
import com.xw.project.service.common.TargetPositionService;
import com.xw.project.entity.TargetPosition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * TargetPosition服务类
 * </p>
 *
 * @author Jy
 * @since 2020-06-28
 */
@Service
public class TargetPositionServiceImpl extends ServiceImpl<TargetPositionMapper, TargetPosition> implements TargetPositionService {

    @Autowired
    private TargetPositionMapper targetPositionMapper;

    @Override
    public IPage<TargetPosition> page(TargetPositionDto targetPositionDto) {
        IPage<TargetPosition> page = new Page<>(targetPositionDto.getCurrent(), targetPositionDto.getSize());
        QueryWrapper<TargetPosition> queryWrapper = new QueryWrapper<>();
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public void add(TargetPosition targetPosition) {
        baseMapper.insert(targetPosition);
    }

    @Override
    public void update(TargetPosition targetPosition) {
        baseMapper.updateById(targetPosition);
    }

    @Override
    public void delete(String id) {
        baseMapper.deleteById(id);
    }

    @Override
    public void updateByRefId(TargetPosition targetPosition) {
        targetPositionMapper.updateByRefId(targetPosition);
    }

    @Override
    public void deleteByRefId(String refId) {
        targetPositionMapper.deleteByRefId(refId);
    }

}