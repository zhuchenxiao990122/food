package com.xw.project.service.organize.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.project.dto.RedCrossMemberDto;
import com.xw.project.entity.RedCrossMemberTeenagers;
import com.xw.project.mapper.RedCrossMemberTeenagersMapper;
import com.xw.project.service.organize.RedCrossMemberTeenagersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.project.vo.RedCrossMemberTeenagersVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 青少年会员 服务实现类
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-06
 */
@Service
public class RedCrossMemberTeenagersServiceImpl extends ServiceImpl<RedCrossMemberTeenagersMapper, RedCrossMemberTeenagers> implements RedCrossMemberTeenagersService {

    @Autowired
    private RedCrossMemberTeenagersMapper memberYoungMapper;

    @Override
    public RestResponse<IPage<RedCrossMemberTeenagersVo>> listRedCrossMemberYoung(RedCrossMemberDto redCrossMemberDto) {
        Page<RedCrossMemberTeenagersVo> page = new Page<>(redCrossMemberDto.getCurrent(), redCrossMemberDto.getSize());
        IPage<RedCrossMemberTeenagersVo> Ipage = memberYoungMapper.listRedCrossMemberGroup(page, redCrossMemberDto);
        return ResultGenerator.genSuccessResult(Ipage);
    }

    /**
     * 获取青少年信息
     *
     * @param memberCode
     * @return
     */
    @Override
    public RestResponse<RedCrossMemberTeenagersVo> getCrossMemberTeenagers(String memberCode) {
        RedCrossMemberTeenagersVo redCrossMemberYoungVo = memberYoungMapper.getCrossMemberTeenagers(memberCode);
        return ResultGenerator.genSuccessResult(redCrossMemberYoungVo);
    }

}

