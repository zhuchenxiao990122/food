package com.xw.project.service.disaster.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.common.util.StringUtil;
import com.xw.common.util.UUIDGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.DisMaterialWarehouseDto;
import com.xw.project.entity.DisMaterialWarehouse;
import com.xw.project.mapper.DisMaterialWarehouseMapper;
import com.xw.project.service.disaster.DisMaterialWarehouseService;
import com.xw.project.vo.DisMaterialWarehouseVo;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.annotation.Documented;
import java.util.List;

/**
 * <p>救灾救援-仓库管理-仓库信息管理-服务实现类
 *
 * @author weiLiang
 * @since 2020-7-21
 */
@Service
public class DisMaterialWarehouseServiceImpl extends ServiceImpl<DisMaterialWarehouseMapper, DisMaterialWarehouse> implements DisMaterialWarehouseService {
    @Autowired
    private DisMaterialWarehouseMapper disMaterialWarehouseMapper;

    @Override
    public IPage<DisMaterialWarehouseVo> pageListWarehouseInfo(DisMaterialWarehouseDto disMaterialWarehouseDto) {
        Page<DisMaterialWarehouse> page = new Page<>(disMaterialWarehouseDto.getCurrent(), disMaterialWarehouseDto.getSize());
        return disMaterialWarehouseMapper.pageListWarehouseInfo(page, disMaterialWarehouseDto);
    }

    @Override
    public String getAreaId(String orgId){
        return disMaterialWarehouseMapper.getAreaId(orgId);
    }

    @Override
    public Integer addWarehouseInfo(DisMaterialWarehouse disMaterialWarehouse) {
        return disMaterialWarehouseMapper.insert(disMaterialWarehouse);
    }

    @Override
    public Integer deleteWarehouseInfoById(String id) {
        DisMaterialWarehouse disMaterialWarehouse = new DisMaterialWarehouse();
        disMaterialWarehouse.setId(id);
        return disMaterialWarehouseMapper.deleteById(disMaterialWarehouse);

    }

    @Override
    public Integer updateWareHouseInfo(DisMaterialWarehouse disMaterialWarehouse) {
        return disMaterialWarehouseMapper.updateById(disMaterialWarehouse);
    }
}
