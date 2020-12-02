package com.xw.project.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.SysActivityDto;
import com.xw.project.entity.SysActivity;
import com.xw.project.mapper.SysActivityMapper;
import com.xw.project.service.system.SysActivityService;
import com.xw.project.vo.SysActivityVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class SysActivityServiceImpl extends ServiceImpl<SysActivityMapper,SysActivity> implements SysActivityService {
    @Autowired
    SysActivityMapper sysActivityMapper;

    @Override
    public RestResponse<IPage<SysActivityVo>> listActivity(SysActivityDto sysActivityDto) {
        Page<SysActivityVo> page = new Page<>(sysActivityDto.getCurrent(), sysActivityDto.getSize());
        try{
            IPage<SysActivityVo> sysActivityVoIPage = sysActivityMapper.search(page, sysActivityDto);
            return ResultGenerator.genSuccessResult(sysActivityVoIPage);
        }catch (Exception e){
            log.error(DocConstant.search_error,e);
            return ResultGenerator.genSuccessResult(DocConstant.search_error);
        }
    }

    @Transactional
    @Override
    public RestResponse<String> addSysActivity(SysActivityVo sysActivityVo)   {
        SysActivity sysActivity = new SysActivity();
        BeanUtils.copyProperties(sysActivityVo, sysActivity);
        QueryWrapper<SysActivity> queryWrapper = new QueryWrapper<SysActivity>(sysActivity);
        List<SysActivity> sysActivities = sysActivityMapper.selectList(queryWrapper);
        List<SysActivity> sysActivities2 =sysActivityMapper.list(sysActivity.getActivityName());

        if (sysActivities.isEmpty() && sysActivities2.isEmpty()) {
            try{
                sysActivityMapper.insert(sysActivity);
                return ResultGenerator.genSuccessResult(DocConstant.add_success);
            }catch (Exception e){
                log.error("发布活动信息出错",e);
                return ResultGenerator.genFailResult("发布活动信息出错");
            }
        }else{
            log.error("活动名称已存在");
            return ResultGenerator.genFailResult("活动名称已存在");
        }
    }

    /**
     * 根据id 查询
     * @param id
     * @return
     */
    @Transactional
    @Override
    public RestResponse<SysActivityVo> getSysActivity(String id) {
        SysActivityVo sysActivityVo =new SysActivityVo();
        SysActivity sysActivity =sysActivityMapper.selectById(id);
        BeanUtils.copyProperties(sysActivity,sysActivityVo);
        return ResultGenerator.genSuccessResult(sysActivityVo);
    }

    /**
     * 修改活动信息
     * @param sysActivityVo
     * @return
     */
    @Override
    @Transactional
    public RestResponse<String> updateSysActivity(SysActivityVo sysActivityVo) {
        SysActivity sysActivity =new SysActivity();
        BeanUtils.copyProperties(sysActivityVo, sysActivity);
        try{
            sysActivityMapper.updateById(sysActivity);
            return ResultGenerator.genSuccessResult(DocConstant.update_success);
        }catch (Exception e){
            log.error(DocConstant.update_error,e);
            return ResultGenerator.genFailResult(DocConstant.update_error);
        }
    }

    /**
     * 删除活动信息
     * @param id
     * @return
     */
    @Transactional
    @Override
    public RestResponse<String> deleteSysActivity(String id) {
        try{
            sysActivityMapper.deleteById(id);
            return ResultGenerator.genSuccessResult(DocConstant.delete_success);
        }catch (Exception e){
            log.error(DocConstant.delete_error,e);
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
    }
}
