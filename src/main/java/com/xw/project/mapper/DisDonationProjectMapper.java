package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.project.dto.DisDonationProjectDto;
import com.xw.project.entity.DisDonationProject;
import com.xw.project.vo.DisDonationProjectVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yuli
 * @since 2020-06-24
 */
public interface DisDonationProjectMapper extends BaseMapper<DisDonationProject> {

    IPage<DisDonationProjectVo> pagePublish(IPage<DisDonationProject> page);

    IPage<DisDonationProjectVo> pageApply(@Param("page") IPage<DisDonationProjectVo> page, @Param("disDonationProjectDto") DisDonationProjectDto disDonationProjectApplyDto);

    List<Map<String, String>> selectListByMap(@Param("map")Map<String, String> map);

}
