package com.xw.project.mapper;

import com.xw.project.entity.DisDonationProjectDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuli
 * @since 2020-07-01
 */
public interface DisDonationProjectDetailMapper extends BaseMapper<DisDonationProjectDetail> {

    void deleteByProjectID(@Param("projectId") String projectId);

}
