package com.xw.project.service.advertise;

import com.xw.common.result.RestResponse;
import com.xw.project.entity.AdvEduLifeHall;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.project.dto.AdvEduLifeHallDto;
import com.xw.project.vo.AdvEduLifeHallVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jy
 */
public interface  AdvEduLifeHallService {

  /**
   * 分页查询
   * @param advEduLifeHallDto
   * @return list<AdvEduLifeHallVo></>
   */
  IPage<AdvEduLifeHallVo> page(AdvEduLifeHallDto advEduLifeHallDto);

  /**
   * 新增
   * @param advEduLifeHallDto
   * @return
   */
  RestResponse<String> add(AdvEduLifeHallDto advEduLifeHallDto);

  /**
   * 修改
   * @param advEduLifeHallDto
   */
  RestResponse<String> update(AdvEduLifeHallDto advEduLifeHallDto);

  /**
   * 删除
   * @param id
   */
  RestResponse<String> delete(String id);

  /**
   * 根据ID查询一条数据
   * @param id
   * @return AdvEduLifeHallVo
   */
  AdvEduLifeHallVo getByIdVo(String id);

  /**
   * 获取省市区的树
   * @return List<AdvEduLifeHallVo>
   */
  List<AdvEduLifeHallVo> listTreeArea();

  /**
   * 查询所有的信息
   * @return List<AdvEduLifeHallVo>
   */
  List<AdvEduLifeHallVo> listAllAddress();
}