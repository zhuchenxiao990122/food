package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.project.dto.AdvPrayLifePlaceDto;
import com.xw.project.entity.AdvPrayLifePlace;
import com.xw.project.entity.AdvPrayLifePlace;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.AdvPrayLifePlaceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jy
 * @since 2020-07-06
 */
public interface AdvPrayLifePlaceMapper extends BaseMapper<AdvPrayLifePlace> {
    IPage<AdvPrayLifePlaceVo> selectPageVO(@Param("pageInfo") IPage<AdvPrayLifePlace> pageInfo, @Param("advPrayLifePlaceDto") AdvPrayLifePlaceDto advPrayLifePlaceDto);

    AdvPrayLifePlaceVo selectByIdVo(@Param("id") String id);

    List<AdvPrayLifePlaceVo> listTreeArea();

    List<AdvPrayLifePlaceVo> listAllAddress();
}
