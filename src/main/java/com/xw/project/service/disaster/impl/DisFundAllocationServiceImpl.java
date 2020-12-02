package com.xw.project.service.disaster.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.common.entity.User;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.DisFundAllocationDto;
import com.xw.project.dto.DisFundAllocationFileDto;
import com.xw.project.entity.ComFile;
import com.xw.project.entity.DisDonationFund;
import com.xw.project.entity.DisFundAllocation;
import com.xw.project.mapper.DisDonationFundMapper;
import com.xw.project.mapper.DisFundAllocationMapper;
import com.xw.project.service.common.impl.ComFileServiceImpl;
import com.xw.project.service.disaster.DisFundAllocationService;
import com.xw.system.util.AppUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * DisFundAllocation服务类
 * </p>
 *
 * @author yuli
 * @since 2020-07-23
 */
@Service
public class DisFundAllocationServiceImpl extends ServiceImpl<DisFundAllocationMapper, DisFundAllocation> implements DisFundAllocationService {
    @Autowired
    private ComFileServiceImpl comFileServiceImpl;
    @Autowired
    private AppUtil appUtil;
    @Autowired
    private DisDonationFundMapper disDonationFundMapper;

    @Override
    public IPage<DisFundAllocation> page(DisFundAllocationDto disFundAllocationDto) {
        IPage<DisFundAllocation> page = new Page<>(disFundAllocationDto.getCurrent(), disFundAllocationDto.getSize());
        QueryWrapper<DisFundAllocation> queryWrapper = new QueryWrapper<>();
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public void add(DisFundAllocation disFundAllocation) {
        baseMapper.insert(disFundAllocation);
    }

    @Override
    public void update(DisFundAllocation disFundAllocation) {
        baseMapper.updateById(disFundAllocation);
    }

    @Override
    public void delete(String id) {
        baseMapper.deleteById(id);
    }

    @Override
    @Transactional
    public RestResponse<String> save(DisFundAllocationFileDto disFundAllocationFileDto) {
        //保存附件
        List<String> fileIds = disFundAllocationFileDto.getFileIds();
        String id = disFundAllocationFileDto.getId();
        List<ComFile> list = new ArrayList<>();
        if (null != fileIds) {
            for (String fileId : fileIds) {
                ComFile comFile = new ComFile();
                comFile.setRefId(id);
                comFile.setFileId(fileId);
                list.add(comFile);
            }
        }
        //保存基本信息
        try {
            DisFundAllocation disFundAllocation = new DisFundAllocation();
            BeanUtils.copyProperties(disFundAllocationFileDto, disFundAllocation);
            baseMapper.insert(disFundAllocation);
            comFileServiceImpl.saveBatch(list);

        } catch (Exception e) {
            log.error(DocConstant.add_error, e);
            return ResultGenerator.genFailResult(DocConstant.add_error);
        }
        return ResultGenerator.genSuccessResult(DocConstant.add_success);

    }

    @Override
    public RestResponse<List<DisDonationFund>> listFund(BigDecimal amount, String projectId) {
        User user = appUtil.getUser();
        String userOrg = user.getUserOrg();
        Map map = new HashMap();
        map.put("project_id", projectId);
        map.put("org_id", userOrg);
        map.put("del_flag", "0");
        return ResultGenerator.genSuccessResult(disDonationFundMapper.selectByMap(map));

    }

}