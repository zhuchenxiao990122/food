package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.project.dto.RtmAedDto;
import com.xw.project.entity.RtmAed;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.RtmAedVo;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jy
 * @since 2020-06-23
 */
public interface RtmAedMapper extends BaseMapper<RtmAed> {

    /**
     * 返回选择区域的数据
     * @return
     */
    List<RtmAedVo> listTreeArea();

    /**
     * 获取所有的地区信息
     * @return
     */
    List<RtmAedVo> listAllAddress();

    /**
     * 分页查询
     * @param pageInfo
     * @param rtmAedDto
     * @return
     */
    IPage<RtmAedVo> selectPageVO(@Param("pageInfo") Page<RtmAedVo> pageInfo,@Param("rtmAedDto") RtmAedDto rtmAedDto);


    RtmAedVo selectByIdVo(@Param("id") String id);
}
