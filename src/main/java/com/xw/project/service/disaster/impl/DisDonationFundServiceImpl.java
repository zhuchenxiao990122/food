package com.xw.project.service.disaster.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.DisDonationFundDto;
import com.xw.project.dto.DisDonationFundFileDto;
import com.xw.project.entity.ComFile;
import com.xw.project.entity.DisDonationFund;
import com.xw.project.mapper.DisDonationFundMapper;
import com.xw.project.service.common.impl.ComFileServiceImpl;
import com.xw.project.service.disaster.DisDonationFundService;
import com.xw.project.vo.DisDonationFundVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * DisDonationFund服务类
 * </p>
 *
 * @author yuli
 * @since 2020-07-16
 */
@Service
public class DisDonationFundServiceImpl extends ServiceImpl<DisDonationFundMapper, DisDonationFund> implements DisDonationFundService {
    @Autowired
    private ComFileServiceImpl comFileServiceImpl;

    @Override
    public IPage<DisDonationFundVo> page(DisDonationFundDto disDonationFundDto) {
        IPage<DisDonationFundVo> page = new Page<>(disDonationFundDto.getCurrent(), disDonationFundDto.getSize());
        return baseMapper.selectPageByCondition(page, disDonationFundDto);
    }

    @Override
    public RestResponse<String> add(DisDonationFundFileDto disDonationFundFileDto) {
        //保存附件
        List<String> fileIds = disDonationFundFileDto.getFileIds();
        String id = disDonationFundFileDto.getId();
        List<ComFile> list = new ArrayList<>();
        if (null != fileIds) {
            for (String fileId : fileIds) {
                ComFile comFile = new ComFile();
                comFile.setRefId(id);
                comFile.setFileId(fileId);
                list.add(comFile);
            }
        }
        //保存资金管理信息
        try {
            DisDonationFund disDonationFund = new DisDonationFund();
            BeanUtils.copyProperties(disDonationFundFileDto, disDonationFund);
            baseMapper.insert(disDonationFund);
            comFileServiceImpl.saveBatch(list);

        } catch (Exception e) {
            log.error(DocConstant.add_error, e);
            return ResultGenerator.genFailResult(DocConstant.add_error);
        }
        return ResultGenerator.genSuccessResult(DocConstant.add_success);
    }

    @Override
    public void update(DisDonationFund disDonationFund) {
        baseMapper.updateById(disDonationFund);
    }

    @Override
    public void delete(String id) {
        baseMapper.deleteById(id);
    }

}