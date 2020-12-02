package com.xw.project.service.rescuetrain;

import com.xw.common.result.RestResponse;
import com.xw.project.entity.RtmAed;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.project.dto.RtmAedDto;
import com.xw.project.vo.RtmAedVo;

import java.util.List;

/**
 * @author Jy
 */
public interface  RtmAedService {

  /**
   * 分页查询
   * @param rtmAedDto
   * @return list<RtmAedVo>
   */
  IPage<RtmAedVo> page(RtmAedDto rtmAedDto);

  /**
   * 新增
   * @param rtmAedDto
   */
  RestResponse<String> add(RtmAedDto rtmAedDto);

  /**
   * 修改
   * @param rtmAedDto
   */
  RestResponse<String> update(RtmAedDto rtmAedDto);

  /**
   * 删除
   * @param id
   */
  RestResponse<String> delete(String id);

  /**
   * 获取省市区的树
   * @return List<RtmAedVo>
   */
  List<RtmAedVo> listTreeArea();

  /**
   * 查询所有的信息
   * @return List<RtmAedVo>
   */
  List<RtmAedVo> listAllAddress();

  /**
   * 根据ID查询一条数据
   * @param id
   * @return RtmAedVo
   */
  RtmAedVo getByIdVo(String id);
}