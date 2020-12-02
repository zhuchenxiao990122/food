package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.project.dto.AdvEduLifeHallDto;
import com.xw.project.entity.AdvEduLifeHall;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.AdvEduLifeHallVo;
import com.xw.project.vo.RtmAedVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jy
 * @since 2020-06-30
 */
public interface AdvEduLifeHallMapper extends BaseMapper<AdvEduLifeHall> {

    IPage<AdvEduLifeHallVo> selectPageVO(@Param("pageInfo") IPage<AdvEduLifeHall> pageInfo,@Param("advEduLifeHallDto") AdvEduLifeHallDto advEduLifeHallDto);

    AdvEduLifeHallVo selectByIdVo(@Param("id") String id);

    List<AdvEduLifeHallVo> listTreeArea();

    List<AdvEduLifeHallVo> listAllAddress();
}
