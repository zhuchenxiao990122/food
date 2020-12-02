package com.xw.project.service.disaster.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.DisDonationProjectDetailDto;
import com.xw.project.entity.DisDonationProject;
import com.xw.project.entity.DisDonationProjectDetail;
import com.xw.project.mapper.DisDonationProjectDetailMapper;
import com.xw.project.mapper.DisDonationProjectMapper;
import com.xw.project.service.disaster.DisDonationProjectDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * DisDonationProjectDetail服务类
 * </p>
 *
 * @author yuli
 * @since 2020-07-01
 */
@Service
public class DisDonationProjectDetailServiceImpl extends ServiceImpl<DisDonationProjectDetailMapper, DisDonationProjectDetail> implements DisDonationProjectDetailService {
    @Autowired
    private DisDonationProjectMapper disDonationProjectMapper;

    @Override
    public void update(DisDonationProjectDetail disDonationProjectDetail) {
        baseMapper.updateById(disDonationProjectDetail);
    }

    @Override
    public void delete(String id) {
        baseMapper.deleteById(id);
    }

    @Override
    @Transactional
    public RestResponse save(DisDonationProjectDetailDto disDonationProjectDetailDto) {
        //删除物资详细原有数据
        baseMapper.deleteByProjectID(disDonationProjectDetailDto.getProjectId());
        //插入物资捐赠详细
        for (DisDonationProjectDetail disDonationProjectDetail : disDonationProjectDetailDto.getDisDonationProjectDetail()) {
            try {
                baseMapper.insert(disDonationProjectDetail);
            } catch (Exception e) {
                log.error(DocConstant.add_error, e);
                return ResultGenerator.genFailResult(DocConstant.add_error);
            }
        }
        //更新捐赠物资项目信息
        DisDonationProject disDonationProject = new DisDonationProject();
        disDonationProject.setId(disDonationProjectDetailDto.getProjectId());
        disDonationProject.setType(disDonationProjectDetailDto.getDonationType());
        try {
            disDonationProjectMapper.updateById(disDonationProject);
            return ResultGenerator.genSuccessResult(DocConstant.add_success);
        } catch (Exception e) {
            log.error(DocConstant.add_error, e);
            return ResultGenerator.genFailResult(DocConstant.add_error);
        }
    }

    @Override
    public List<DisDonationProjectDetail> search(String projectId) {
        Map<String, Object> map = new HashMap<>();
        map.put("project_id", projectId);
        map.put("del_flag", "0");
        return baseMapper.selectByMap(map);
    }

}