package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.project.dto.RedCrossMemberDto;
import com.xw.project.entity.RedCrossMemberAdult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.RedCrossMemberAdultVo;
import com.xw.project.vo.RedCrossMemberVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

/**
 * <p>
 * adult_member 成人会员表 Mapper 接口
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-06
 */
public interface RedCrossMemberAdultMapper extends BaseMapper<RedCrossMemberAdult> {

    IPage<RedCrossMemberAdultVo> listRedCrossMemberAdult(@Param(value = "page") Page<RedCrossMemberAdultVo> page,
                                                         @Param(value = "redCrossMemberDto") RedCrossMemberDto redCrossMemberDto);


    RedCrossMemberVo getCrossMemberAdult(@Param(value = "memberCode") String memberCode);
}
