package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.project.dto.RedCrossMemberDto;
import com.xw.project.entity.RedCrossMemberTeenagers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.RedCrossMemberTeenagersVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 青少年会员 Mapper 接口
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-06
 */
public interface RedCrossMemberTeenagersMapper extends BaseMapper<RedCrossMemberTeenagers> {

    IPage<RedCrossMemberTeenagersVo> listRedCrossMemberGroup(@Param(value = "page") Page<RedCrossMemberTeenagersVo> page,
                                                             @Param(value = "redCrossMemberDto") RedCrossMemberDto redCrossMemberDto);

    RedCrossMemberTeenagersVo getCrossMemberTeenagers(@Param(value = "memberCode") String memberCode);
}
