package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.project.dto.AdvRcParkDto;
import com.xw.project.entity.AdvRcPark;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.AdvRcParkVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jy
 * @since 2020-07-03
 */
public interface AdvRcParkMapper extends BaseMapper<AdvRcPark> {

    /**
     * 分页
     * @param pageInfo
     * @param advRcParkDto
     * @return
     */
    IPage<AdvRcParkVo> selectPageVO(@Param("pageInfo") IPage<AdvRcParkVo> pageInfo,@Param("advRcParkDto") AdvRcParkDto advRcParkDto);

    /**
     * 查出省市区
     * @return
     */
    List<AdvRcParkVo> listTreeArea();

    /**
     * 查出所有地址
     * @return
     */
    List<AdvRcParkVo> listAllAddress();

    /**
     * 获取一条信息详情
     * @param id
     * @return
     */
    AdvRcParkVo getByIdVo(@Param("id") String id);
}
