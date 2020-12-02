package com.xw.project.service.disaster.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.common.entity.Dict;
import com.xw.common.mapper.DictMapper;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.UUIDGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.DisRescueRequestDto;
import com.xw.project.entity.DisDisasterPublish;
import com.xw.project.entity.DisRescueRequest;
import com.xw.project.mapper.DisDisasterPublishMapper;
import com.xw.project.mapper.DisRescueRequestMapper;
import com.xw.project.service.disaster.DisRescueRequestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.project.vo.DisRescueRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author caoshuhao
 * @since 2020-06-28
 */
@Service
public class DisRescueRequestServiceImpl extends ServiceImpl<DisRescueRequestMapper, DisRescueRequest> implements DisRescueRequestService {

    @Autowired
    private DisRescueRequestMapper disRescueRequestMapper;
    @Autowired
    private DisDisasterPublishMapper disDisasterPublishMapper;
    @Autowired
    private DictMapper dictMapper;

    @Override
    public RestResponse<IPage<DisRescueRequestVo>> search(DisRescueRequestDto disRescueRequestDto) {
        Page<DisRescueRequestVo> page = new Page<>(disRescueRequestDto.getCurrent(), disRescueRequestDto.getSize());
        IPage<DisRescueRequestVo> disRescueRequestVoIPage = disRescueRequestMapper.search(page, disRescueRequestDto);
        if (disRescueRequestVoIPage.getRecords() == null || disRescueRequestVoIPage.getRecords().size() < 1) {
            return ResultGenerator.genSuccessResult(disRescueRequestVoIPage);
        }
        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.lambda().eq(Dict::getType, "rescue_apply_number");
        Dict rescueApplyNumber = dictMapper.selectOne(dictQueryWrapper);
        String name = rescueApplyNumber.getName();
        String[] ns = name.split("n");
        List<DisRescueRequestVo> records = disRescueRequestVoIPage.getRecords();
        for (DisRescueRequestVo record : records) {
            record.setApplyName(ns[0] + record.getApplyNumber() + ns[1]);
        }
        return ResultGenerator.genSuccessResult(disRescueRequestVoIPage);
    }

    /**
     * 救助请求新增
     * @param disRescueRequestVo
     * @return
     */
    @Override
    public String addDisRescueRequest(DisRescueRequestVo disRescueRequestVo) {
        String publishOrg = disRescueRequestVo.getId();
        QueryWrapper<DisDisasterPublish> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(DisDisasterPublish::getPublishOrg,publishOrg).eq(DisDisasterPublish::getPublishCode,disRescueRequestVo.getPublishCode());
        DisDisasterPublish disDisasterPublish = disDisasterPublishMapper.selectOne(queryWrapper);
        String publishId = disDisasterPublish.getId();
        String applyNumber = disRescueRequestMapper.selectMaxApplyNumber(publishId);
        if (applyNumber == null) {
            applyNumber = "1";
        } else {
            applyNumber = (Integer.valueOf(applyNumber) + 1) + "";
        }
        List<DisRescueRequest> disRescueRequests = disRescueRequestVo.getDisRescueRequests();
        for (DisRescueRequest rescueRequest : disRescueRequests) {
            rescueRequest.setPublishId(publishId);
            rescueRequest.setApplyNumber(applyNumber);
            rescueRequest.setDelFlag("0");
        }
        this.saveBatch(disRescueRequests);
        return publishId;
    }

    /**
     * 查询每次资助请求 物资
     * @param publishId
     * @param applyNumber
     * @return
     */
    @Override
    public RestResponse<DisRescueRequestVo> getRescueRequest(String publishId, String applyNumber) {
        DisRescueRequestVo disRescueRequestVo = new DisRescueRequestVo();
        QueryWrapper<DisRescueRequest> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(DisRescueRequest::getPublishId, publishId).eq(DisRescueRequest::getApplyNumber, applyNumber).orderByDesc(DisRescueRequest::getCreateDate);
        List<DisRescueRequest> disRescueRequests = disRescueRequestMapper.selectList(queryWrapper);
        disRescueRequestVo.setDisRescueRequests(disRescueRequests);
        return ResultGenerator.genSuccessResult(disRescueRequestVo);
    }

    /**
     * 获取上报编号
     * @return
     */
    @Override
    public RestResponse<List<String>> listPublishCode(String publishOrg) {
        try {
            List<String> publishCodes = disRescueRequestMapper.selectPublishCode(publishOrg);
            return ResultGenerator.genSuccessResult(publishCodes);
        }catch (Exception e){
            log.error(DocConstant.search_error,e);
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
    }

    /**
     * 删除救助申请
     * @param publishId
     * @param applyNumber
     * @return
     */
    @Override
    public RestResponse<String> delete(String publishId, String applyNumber) {
        QueryWrapper<DisRescueRequest> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(DisRescueRequest::getPublishId,publishId).eq(DisRescueRequest::getApplyNumber,applyNumber);
        try{
            int delete = disRescueRequestMapper.delete(queryWrapper);
            return ResultGenerator.genSuccessResult(publishId);
        }catch (Exception e){
            log.error(DocConstant.delete_error,e);
            return ResultGenerator.genSuccessResult(DocConstant.delete_error);
        }
    }
}
