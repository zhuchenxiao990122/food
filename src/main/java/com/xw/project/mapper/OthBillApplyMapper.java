package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.project.entity.OthBillApply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.OthBillApplySearchVo;
import com.xw.project.vo.OthBillApplyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dy
 * @since 2020-03-23
 */
public interface OthBillApplyMapper extends BaseMapper<OthBillApply> {

    IPage<OthBillApplyVo> getListBillApply(Page<OthBillApplyVo> pageInfo, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("othBillApplySearchVo")OthBillApplySearchVo othBillApplySearchVo);

    List<Map<String, String>> getAllBillApplyByDate(@Param("startDate") String startDate,@Param("endDate") String endDate);

    List<Map<String, String>> getData();
}
