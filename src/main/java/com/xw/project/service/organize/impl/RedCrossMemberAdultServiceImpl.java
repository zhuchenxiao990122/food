package com.xw.project.service.organize.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.StringUtil;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.RedCrossMemberDto;
import com.xw.project.entity.RedCrossMemberAdult;
import com.xw.project.mapper.RedCrossMemberAdultMapper;
import com.xw.project.mapper.RedCrossMemberMapper;
import com.xw.project.mapper.RedCrossMemberRecordMapper;
import com.xw.project.service.organize.RedCrossMemberAdultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.project.vo.RedCrossMemberAdultVo;
import com.xw.project.vo.RedCrossMemberVo;
import com.xw.system.mapper.OrganizeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * adult_member 成人会员表 服务实现类
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-06
 */
@Slf4j
@Service
public class RedCrossMemberAdultServiceImpl extends ServiceImpl<RedCrossMemberAdultMapper, RedCrossMemberAdult> implements RedCrossMemberAdultService {

    @Autowired
    private RedCrossMemberMapper memberMapper;
    @Autowired
    private OrganizeMapper organizeMapper;
    @Autowired
    private RedCrossMemberAdultMapper memberAdultMapper;
    @Autowired
    private RedCrossMemberRecordMapper memberRecordMapper;


    @Override
    public RestResponse<IPage<RedCrossMemberAdultVo>> listRedCrossMemberAdult(RedCrossMemberDto redCrossMemberDto) {
        Page<RedCrossMemberAdultVo> page = new Page<>(redCrossMemberDto.getCurrent(), redCrossMemberDto.getSize());
        IPage<RedCrossMemberAdultVo> Ipage = memberAdultMapper.listRedCrossMemberAdult(page, redCrossMemberDto);
        List<RedCrossMemberAdultVo> records = Ipage.getRecords();
        if(records.size()>0){
            for (RedCrossMemberAdultVo record : records) {
                if(StringUtil.isNotEmpty(record.getSex())){
                    record.setGender(record.getSex().equals("0")?"女":"男");
                }

            }
        }
        return ResultGenerator.genSuccessResult(Ipage);

    }


    @Override
    public RestResponse<RedCrossMemberVo> getCrossMemberAdult(String memberCode) {
        try {
            RedCrossMemberVo redCrossMemberVo = memberAdultMapper.getCrossMemberAdult(memberCode);
            return ResultGenerator.genSuccessResult(redCrossMemberVo);
        } catch (Exception e) {
            log.error(DocConstant.search_error, e);
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
    }


}
