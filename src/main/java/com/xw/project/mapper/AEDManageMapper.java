package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.entity.AEDManage;
import com.xw.project.entity.RtmAed;
import com.xw.project.vo.AEDManageVo;
import com.xw.project.vo.RtmAedVo;

import java.util.List;

public interface AEDManageMapper  extends BaseMapper<RtmAed> {
    List<RtmAedVo> getTreeArea();

    List<RtmAed> getAllAddress();
}
