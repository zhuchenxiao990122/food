package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.project.dto.RedCrossMemberDto;
import com.xw.project.entity.RedCrossMemberRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.RedCrossMemberAdultVo;
import com.xw.project.vo.RedCrossMemberGroupVo;
import com.xw.project.vo.RedCrossMemberTeenagersVo;
import com.xw.project.vo.RedCrossMemberVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 会员信息基本表 Mapper 接口
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-07
 */
public interface RedCrossMemberRecordMapper extends BaseMapper<RedCrossMemberRecord> {

    IPage<RedCrossMemberVo> listAdultMember(@Param(value = "page") Page<RedCrossMemberVo> page,
                                            @Param(value = "memberDto") RedCrossMemberDto memberDto);

    IPage<RedCrossMemberVo> listTeenagersMember(@Param(value = "page") Page<RedCrossMemberVo> page,
                                                @Param(value = "memberDto") RedCrossMemberDto memberDto);

    IPage<RedCrossMemberVo> listGroupMember(@Param(value = "page") Page<RedCrossMemberVo> page,
                                            @Param(value = "memberDto") RedCrossMemberDto memberDto);

    RedCrossMemberAdultVo getAdultMember(@Param(value = "id") String id);

    RedCrossMemberTeenagersVo getTeenagersMember(@Param(value = "id") String id);

    RedCrossMemberGroupVo getGroupMember(@Param(value = "id") String id);

    int memberPendingCount(@Param(value = "memberType") String memberType,@Param(value = "applyType")String applyType);
}
