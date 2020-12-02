package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.project.dto.DisPublishDto;
import com.xw.project.entity.DisDisasterPublish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.DisPublishVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caoshuhao
 * @since 2020-06-23
 */
@Component
public interface DisDisasterPublishMapper extends BaseMapper<DisDisasterPublish> {

    IPage<DisPublishVo> search( @Param("page")Page<DisPublishVo> page,
                                @Param("disPublishDto") DisPublishDto disPublishDto);
}
