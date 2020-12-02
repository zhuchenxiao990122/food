package com.xw.project.service.organize.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.project.dto.RedCrossMemberDto;
import com.xw.project.entity.RedCrossMemberGroup;
import com.xw.project.mapper.RedCrossMemberGroupMapper;
import com.xw.project.service.organize.RedCrossMemberGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.project.vo.RedCrossMemberAdultVo;
import com.xw.project.vo.RedCrossMemberGroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 团体会员 服务实现类
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-06
 */
@Service
public class RedCrossMemberGroupServiceImpl extends ServiceImpl<RedCrossMemberGroupMapper, RedCrossMemberGroup> implements RedCrossMemberGroupService {

    @Autowired
    private RedCrossMemberGroupMapper memberGroupMapper;

    /**
     * @param redCrossMemberDto
     * @return
     */
    @Override
    public RestResponse<IPage<RedCrossMemberAdultVo>> listRedCrossMemberGroup(RedCrossMemberDto redCrossMemberDto) {
        Page<RedCrossMemberAdultVo> page = new Page<>(redCrossMemberDto.getCurrent(), redCrossMemberDto.getSize());
        IPage<RedCrossMemberAdultVo> Ipage = memberGroupMapper.listRedCrossMemberGroup(page, redCrossMemberDto);
        return ResultGenerator.genSuccessResult(Ipage);
    }

    /**
     * 获取团体会员信息
     *
     * @param memberCode
     * @return
     */
    @Override
    public RestResponse<RedCrossMemberGroupVo> getCrossMemberGroup(String memberCode) {
        return ResultGenerator.genSuccessResult( memberGroupMapper.getCrossMemberGroup(memberCode));
    }
}
