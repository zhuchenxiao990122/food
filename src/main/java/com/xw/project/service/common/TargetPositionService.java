package com.xw.project.service.common;

import com.xw.project.entity.TargetPosition;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.project.dto.TargetPositionDto;

public interface TargetPositionService {

    IPage<TargetPosition> page(TargetPositionDto targetPositionDto);

    void add(TargetPosition targetPosition);

    void update(TargetPosition targetPosition);

    void delete(String id);

    /**
     * 根据refId更新数据
     *
     * @param targetPosition
     */
    void updateByRefId(TargetPosition targetPosition);

    /**
     * 根据refId删除数据
     *
     * @param refId
     */
    void deleteByRefId(String refId);
}