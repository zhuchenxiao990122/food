package com.xw.project.service.advertise;

import com.xw.common.result.RestResponse;
import com.xw.project.dto.AdvPrayLifePlaceDto;
import com.xw.project.entity.AdvPrayLifePlace;
import com.xw.project.mapper.AdvPrayLifePlaceMapper;
import com.xw.project.service.advertise.AdvPrayLifePlaceService;
import com.xw.project.entity.AdvPrayLifePlace;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.project.dto.AdvPrayLifePlaceDto;
import com.xw.project.vo.AdvPrayLifePlaceVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

  import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface  AdvPrayLifePlaceService {
  /**
   * 分页查询
   * @param advPrayLifePlaceDto
   * @return list<AdvPrayLifePlaceVo></>
   */
  IPage<AdvPrayLifePlaceVo> page(AdvPrayLifePlaceDto advPrayLifePlaceDto);

  /**
   * 新增
   * @param advPrayLifePlaceDto
   */
  RestResponse<String> add(AdvPrayLifePlaceDto advPrayLifePlaceDto);

  /**
   * 修改
   * @param advPrayLifePlaceDto
   */
  RestResponse<String> update(AdvPrayLifePlaceDto advPrayLifePlaceDto);

  /**
   * 删除
   * @param id
   */
  RestResponse<String> delete(String id);

  /**
   * 根据ID查询一条数据
   * @param id
   * @return AdvPrayLifePlaceVo
   */
  AdvPrayLifePlaceVo getByIdVo(String id);

  /**
   * 获取省市区的树
   * @return List<AdvPrayLifePlaceVo>
   */
  List<AdvPrayLifePlaceVo> listTreeArea();

  /**
   * 查询所有的信息
   * @return List<AdvPrayLifePlaceVo>
   */
  List<AdvPrayLifePlaceVo> listAllAddress();
}