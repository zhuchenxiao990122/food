package com.xw.project.service.disaster.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.UUIDGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.DisPublishDto;
import com.xw.project.entity.DisDisasterPublish;
import com.xw.project.entity.DisRescueRequest;
import com.xw.project.mapper.DisDisasterPublishMapper;
import com.xw.project.mapper.DisRescueRequestMapper;
import com.xw.project.service.disaster.DisDisasterPublishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.project.vo.DisPublishVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 * 灾情发布
 * </p>
 *
 * @author caoshuhao
 * @since 2020-06-23
 */
@Service
public class DisDisasterPublishServiceImpl extends ServiceImpl<DisDisasterPublishMapper, DisDisasterPublish> implements DisDisasterPublishService {

    @Autowired
    private DisDisasterPublishMapper disDisasterPublishMapper;
    @Autowired
    private DisRescueRequestMapper disRescueRequestMapper;

    @Override
    public RestResponse<IPage<DisPublishVo>> search(DisPublishDto disPublishDto) {
        Page<DisPublishVo> page = new Page<>(disPublishDto.getCurrent(), disPublishDto.getSize());
        try{
            IPage<DisPublishVo> disPublishVoIPage = disDisasterPublishMapper.search(page, disPublishDto);
            return ResultGenerator.genSuccessResult(disPublishVoIPage);
        }catch (Exception e){
            log.error(DocConstant.search_error,e);
            return ResultGenerator.genSuccessResult(DocConstant.search_error);
        }
    }

    /**
     * 新增灾情信息
     * @param disPublishVo
     * @return
     */
    @Transactional
    @Override
    public RestResponse<String> addDisasterPublish(DisPublishVo disPublishVo)   {
        DisDisasterPublish disDisasterPublish = new DisDisasterPublish();
        BeanUtils.copyProperties(disPublishVo, disDisasterPublish);
        disDisasterPublish.setId(null);
        String publishCode = disPublishVo.getPublishCode();
        QueryWrapper<DisDisasterPublish> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(DisDisasterPublish::getPublishCode, publishCode);
        List<DisDisasterPublish> disDisasterPublishes = disDisasterPublishMapper.selectList(queryWrapper);
        if (disDisasterPublishes == null || disDisasterPublishes.size() == 0) {
            try{
                disDisasterPublishMapper.insert(disDisasterPublish);
            }catch (Exception e){
                log.error("新增灾情信息出错",e);
                return ResultGenerator.genFailResult(DocConstant.add_error);
            }
            String id = disDisasterPublish.getId();
            List<DisRescueRequest> disRescueRequests = disPublishVo.getDisRescueRequests();
            if (!(disRescueRequests == null || disRescueRequests.size() == 0)) {
                for (DisRescueRequest disRescueRequest : disRescueRequests) {
                    disRescueRequest.setId(UUIDGenerator.getUUID());
                    disRescueRequest.setPublishId(id);
                    disRescueRequest.setApplyNumber("1");
                }
                try{
                    disRescueRequestMapper.addDisRescueRequest(disRescueRequests);
                }catch (Exception e){
                    log.error("新增灾情信息救助请求时出错",e);
                    return ResultGenerator.genFailResult(DocConstant.add_error);
                }
            }
            return ResultGenerator.genSuccessResult(DocConstant.add_success);
        }else{
            return ResultGenerator.genFailResult(DocConstant.add_error);
        }
    }

    /**
     * 根据id 查询
     * @param id
     * @return
     */
    @Transactional
    @Override
    public RestResponse<DisPublishVo> getDisPublish(String id) {
        DisPublishVo disPublishVo = new DisPublishVo();
        DisDisasterPublish disDisasterPublish = disDisasterPublishMapper.selectById(id);
        BeanUtils.copyProperties(disDisasterPublish, disPublishVo);
        QueryWrapper<DisRescueRequest> qw = new QueryWrapper<>();
        qw.lambda().eq(DisRescueRequest::getPublishId, id).eq(DisRescueRequest::getDelFlag, '0');
        List<DisRescueRequest> disRescueRequests = disRescueRequestMapper.selectList(qw);
        disPublishVo.setDisRescueRequests(disRescueRequests);
        return ResultGenerator.genSuccessResult(disPublishVo);
    }

    /**
     * 修改灾情信息
     * @param disPublishVo
     * @return
     */
    @Override
    @Transactional
    public RestResponse<String> updateDisPublish(DisPublishVo disPublishVo) {
        DisDisasterPublish disDisasterPublish = new DisDisasterPublish();
        BeanUtils.copyProperties(disPublishVo, disDisasterPublish);
        try{
            disDisasterPublishMapper.updateById(disDisasterPublish);
            return ResultGenerator.genSuccessResult(DocConstant.update_success);
        }catch (Exception e){
            log.error(DocConstant.update_error,e);
            return ResultGenerator.genFailResult(DocConstant.update_error);
        }
    }

    /**
     * 删除灾情信息
     * @param id
     * @return
     */
    @Transactional
    @Override
    public RestResponse<String> deleteDisPublish(String id) {
        try{
            disDisasterPublishMapper.deleteById(id);
            QueryWrapper<DisRescueRequest> qw = new QueryWrapper<>();
            qw.lambda().eq(DisRescueRequest::getPublishId, id);
            disRescueRequestMapper.delete(qw);
            return ResultGenerator.genSuccessResult(DocConstant.delete_success);
        }catch (Exception e){
            log.error(DocConstant.delete_error,e);
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
    }
}
