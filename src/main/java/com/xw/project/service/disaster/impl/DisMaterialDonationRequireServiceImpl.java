package com.xw.project.service.disaster.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.common.util.StringUtil;
import com.xw.project.dto.DisMaterialDonationRequireDto;
import com.xw.project.dto.DisMaterialDonationRequireMaterialDto;
import com.xw.project.entity.DisMaterialDonationRequire;
import com.xw.project.mapper.DisMaterialDonationRequireMapper;
import com.xw.project.service.disaster.DisMaterialDonationRequireService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * DisMaterialDonationRequire服务类
 * </p>
 *
 * @author yuli
 * @since 2020-06-30
 */
@Service
public class DisMaterialDonationRequireServiceImpl extends ServiceImpl<DisMaterialDonationRequireMapper, DisMaterialDonationRequire> implements DisMaterialDonationRequireService {

    @Override
    public IPage<DisMaterialDonationRequire> page(DisMaterialDonationRequireDto disMaterialDonationRequireDto) {
        IPage<DisMaterialDonationRequire> page = new Page<>(disMaterialDonationRequireDto.getCurrent(), disMaterialDonationRequireDto.getSize());
        QueryWrapper<DisMaterialDonationRequire> queryWrapper = new QueryWrapper<>();
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public void update(DisMaterialDonationRequire disMaterialDonationRequire) {
        baseMapper.updateById(disMaterialDonationRequire);
    }

    @Override
    public void delete(String id) {
        baseMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(DisMaterialDonationRequireMaterialDto disMaterialDonationRequireMaterialDto) {

        String materialId = disMaterialDonationRequireMaterialDto.getMaterialId();
        if (StringUtil.isEmpty(materialId)) {
            throw new IllegalArgumentException("materialId不能为空");
        }
        List<DisMaterialDonationRequire> disMaterialDonationRequires = disMaterialDonationRequireMaterialDto.getDisMaterialDonationRequires();
        if (null == disMaterialDonationRequires) {
            throw new IllegalArgumentException("DisMaterialDonationRequire实体类不能为空");
        }
        //删除所有已存在的数据
        Map<String, Object> map = new HashMap<>();
        map.put("material_id", materialId);
        baseMapper.deleteByMap(map);
        //新增
        for (DisMaterialDonationRequire disMaterialDonationRequire : disMaterialDonationRequires) {
            baseMapper.insert(disMaterialDonationRequire);
        }
    }

}