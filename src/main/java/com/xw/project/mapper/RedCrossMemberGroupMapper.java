package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.common.result.RestResponse;
import com.xw.project.dto.RedCrossMemberDto;
import com.xw.project.entity.RedCrossMemberGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.RedCrossMemberAdultVo;
import com.xw.project.vo.RedCrossMemberGroupVo;
import org.apache.ibatis.annotations.Param;

import javax.validation.Valid;

/**
 * <p>
 * 团体会员 Mapper 接口
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-06
 */
public interface RedCrossMemberGroupMapper extends BaseMapper<RedCrossMemberGroup> {

    IPage<RedCrossMemberAdultVo> listRedCrossMemberGroup(@Param(value = "page") Page<RedCrossMemberAdultVo> page,
                                                         @Param(value = "redCrossMemberDto") RedCrossMemberDto redCrossMemberDto);

    RedCrossMemberGroupVo getCrossMemberGroup( @Param(value = "memberCode") String memberCode);
}
