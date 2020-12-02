package com.xw.project.service.advertise;

import com.xw.common.result.RestResponse;
import com.xw.project.mapper.AdvRcParkMapper;
import com.xw.project.service.advertise.AdvRcParkService;
import com.xw.project.entity.AdvRcPark;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.project.dto.AdvRcParkDto;
import com.xw.project.vo.AdvRcParkVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface AdvRcParkService {

    /**
     * 分页
     *
     * @param advRcParkDto
     * @return
     */
    IPage<AdvRcParkVo> page(AdvRcParkDto advRcParkDto);

    /**
     * 新增
     *
     * @param advRcParkDto
     * @return
     */
    RestResponse<String> add(AdvRcParkDto advRcParkDto);

    /**
     * 更新
     *
     * @param advRcParkDto
     */
    RestResponse<String> update(AdvRcParkDto advRcParkDto);

    /**
     * 删除
     *
     * @param id
     */
    RestResponse<String> delete(String id);

    /**
     * 根据Id查询一条数据
     * @param id
     * @return
     */
    AdvRcParkVo getByIdVo(String id);

    /**
     * 省市区列表
     * @return
     */
    List<AdvRcParkVo> listTreeArea();

    /**
     * 获取红十字书主题公园的所有地址的信息
     * @return
     */
    List<AdvRcParkVo> listAllAddress();
}