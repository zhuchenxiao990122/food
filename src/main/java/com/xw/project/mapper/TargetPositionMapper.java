package com.xw.project.mapper;

import com.xw.project.entity.TargetPosition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jy
 * @since 2020-06-28
 */
public interface TargetPositionMapper extends BaseMapper<TargetPosition> {

    /**
     * 根据refId更新数据
     * @param targetPosition
     */
    void updateByRefId(TargetPosition targetPosition);

    /**
     * 根据refId删除数据
     * @param refId
     */
    void deleteByRefId(@Param("refId") String refId);
}
