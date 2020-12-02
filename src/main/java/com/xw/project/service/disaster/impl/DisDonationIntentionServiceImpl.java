package com.xw.project.service.disaster.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.DisDonationIntentionDto;
import com.xw.project.entity.ComFile;
import com.xw.project.entity.DisDonationIntention;
import com.xw.project.entity.DisDonationIntentionDetail;
import com.xw.project.mapper.ComFileMapper;
import com.xw.project.mapper.DisDonationIntentionDetailMapper;
import com.xw.project.mapper.DisDonationIntentionMapper;
import com.xw.project.mapper.DisMaterialCategorysMapper;
import com.xw.project.service.disaster.DisDonationIntentionService;
import com.xw.project.vo.DisDonationIntentionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * OthDonationIntention服务类
 * </p>
 *
 * @author yuli
 * @since 2020-07-14
 */
@Service
public class DisDonationIntentionServiceImpl extends ServiceImpl<DisDonationIntentionMapper, DisDonationIntention> implements DisDonationIntentionService {
    @Autowired
    private ComFileMapper comFileMapper;
    @Autowired
    private DisDonationIntentionDetailMapper disDonationIntentionDetailMapper;
    @Autowired
    private DisMaterialCategorysMapper disMaterialCategorysMapper;

    @Override
    public IPage<DisDonationIntentionVo> page(DisDonationIntentionDto disDonationIntentionDto) {
        IPage<DisDonationIntentionVo> page = new Page<>(disDonationIntentionDto.getCurrent(), disDonationIntentionDto.getSize());

        IPage<DisDonationIntentionVo> disDonationIntentionVoIPage = baseMapper.selectPageByCondition(page, disDonationIntentionDto);
        List<DisDonationIntentionVo> disDonationIntentionVos = disDonationIntentionVoIPage.getRecords();

        for (DisDonationIntentionVo disDonationIntentionVo : disDonationIntentionVos) {
            //传入保存的文件
            Map map = new HashMap();
            map.put("ref_id", disDonationIntentionVo.getId());
            map.put("del_flag", "0");
            disDonationIntentionVo.setFileIds(comFileMapper.selectByMap(map));
            //传入意向物资
            Map disDonationIntentionDetailMap = new HashMap();
            disDonationIntentionDetailMap.put("intention_id", disDonationIntentionVo.getId());
            disDonationIntentionDetailMap.put("del_flag", "0");
            List<DisDonationIntentionDetail> disDonationIntentionDetails = disDonationIntentionDetailMapper.selectByMap(disDonationIntentionDetailMap);
            List<Map> lists = new ArrayList<>();
            for (DisDonationIntentionDetail disDonationIntentionDetail : disDonationIntentionDetails) {
                String categoryIds = disDonationIntentionDetail.getCategoryIds();
                Map result = JSON.parseObject(JSON.toJSONString(disDonationIntentionDetail), Map.class);
                result.put("categoryIds", categoryIds.split(","));
                lists.add(result);
            }

            disDonationIntentionVo.setIntentionDetails(lists);
        }
        return disDonationIntentionVoIPage;
    }

    @Override
    public void update(DisDonationIntention disDonationIntention) {
        baseMapper.updateById(disDonationIntention);
    }

    @Override
    public void delete(String id) {
        baseMapper.deleteById(id);
    }

    @Override
    public RestResponse<String> add(DisDonationIntention disDonationIntention) {
        DisDonationIntention othDonationIntentionOld = baseMapper.selectById(disDonationIntention.getId());
        try {
            if (null != othDonationIntentionOld) {
                //数据库已存在则更新，否则新增
                baseMapper.updateById(disDonationIntention);
            } else {
                baseMapper.insert(disDonationIntention);
            }
        } catch (Exception e) {
            log.error(DocConstant.add_error, e);
            return ResultGenerator.genFailResult(DocConstant.add_error);
        }
        return ResultGenerator.genSuccessResult(DocConstant.add_success);
    }

    @Override
    public RestResponse<String> addFile(ComFile comFile) {
        try {
            comFileMapper.insert(comFile);
        } catch (Exception e) {
            log.error(DocConstant.add_error, e);
            return ResultGenerator.genFailResult(DocConstant.add_error);
        }
        return ResultGenerator.genSuccessResult(DocConstant.add_success);
    }

    @Override
    public RestResponse<String> deleteFile(ComFile comFile) {
        Map map = new HashMap();
        map.put("ref_id", comFile.getRefId());
        map.put("categories", comFile.getCategories());
        map.put("file_id", comFile.getFileId());
        try {
            comFileMapper.deleteByMap(map);
        } catch (Exception e) {
            log.error(DocConstant.delete_error, e);
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
        return ResultGenerator.genSuccessResult(DocConstant.delete_success);

    }

}