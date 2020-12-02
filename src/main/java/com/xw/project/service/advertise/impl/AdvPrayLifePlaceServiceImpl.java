package com.xw.project.service.advertise.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.AdvPrayLifePlaceDto;
import com.xw.project.dto.AdvPrayLifePlaceDto;
import com.xw.project.entity.AdvPrayLifePlace;
import com.xw.project.entity.TargetPosition;
import com.xw.project.mapper.AdvPrayLifePlaceMapper;
import com.xw.project.mapper.AdvPrayLifePlaceMapper;
import com.xw.project.mapper.TargetPositionMapper;
import com.xw.project.service.advertise.AdvPrayLifePlaceService;
import com.xw.project.entity.AdvPrayLifePlace;

import com.xw.project.vo.AdvPrayLifePlaceVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import java.util.Date;

import com.baomidou.mybatisplus.extension.service.IService;

/**
* <p>
    * AdvPrayLifePlace服务类
    * </p>
*
* @author Jy
* @since 2020-07-06
*/
@Service
public class AdvPrayLifePlaceServiceImpl extends ServiceImpl<AdvPrayLifePlaceMapper, AdvPrayLifePlace> implements AdvPrayLifePlaceService {
  @Autowired
  private AdvPrayLifePlaceMapper advPrayLifePlaceMapper;

  @Autowired
  private TargetPositionMapper targetPositionMapper;

  @Override
  public IPage<AdvPrayLifePlaceVo> page(AdvPrayLifePlaceDto advPrayLifePlaceDto){
    IPage<AdvPrayLifePlace> pageInfo = new Page<>(advPrayLifePlaceDto.getCurrent(), advPrayLifePlaceDto.getSize());
    IPage<AdvPrayLifePlaceVo> page = this.advPrayLifePlaceMapper.selectPageVO(pageInfo,advPrayLifePlaceDto);
    return page;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public RestResponse<String> add(AdvPrayLifePlaceDto advPrayLifePlaceDto){
    AdvPrayLifePlace advPrayLifePlace = new AdvPrayLifePlace();
    TargetPosition targetPosition = new TargetPosition();
    //将前台数据复制到基本信息表中 advPrayLifePlaceDto->advPrayLifePlace
    BeanUtils.copyProperties(advPrayLifePlaceDto, advPrayLifePlace);
    //将前台数据复制到基本信息表中 advPrayLifePlaceDto->targetPosition
    BeanUtils.copyProperties(advPrayLifePlaceDto, targetPosition);
    //获取刚插入的主键ID
    try {
      advPrayLifePlaceMapper.insert(advPrayLifePlace);
      targetPosition.setRefId(advPrayLifePlace.getId());
      targetPositionMapper.insert(targetPosition);
      return ResultGenerator.genSuccessResult(DocConstant.add_success);
    } catch (Exception e) {
      log.error(DocConstant.add_error, e);
      return ResultGenerator.genFailResult(DocConstant.add_error);
    }
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public RestResponse<String> update(AdvPrayLifePlaceDto advPrayLifePlaceDto){
    AdvPrayLifePlace advPrayLifePlace = new AdvPrayLifePlace();
    TargetPosition targetPosition = new TargetPosition();
    //将前台数据复制到aed信息表中 advPrayLifePlaceDto->advPrayLifePlace
    BeanUtils.copyProperties(advPrayLifePlaceDto, advPrayLifePlace);
    //将前台数据复制到基本信息表中 advPrayLifePlaceDto->targetPosition
    BeanUtils.copyProperties(advPrayLifePlaceDto, targetPosition);
    try {
      advPrayLifePlaceMapper.updateById(advPrayLifePlace);
      targetPosition.setRefId(advPrayLifePlaceDto.getId());
      targetPositionMapper.updateByRefId(targetPosition);
      return ResultGenerator.genSuccessResult(DocConstant.update_success);
    } catch (Exception e) {
      log.error(DocConstant.update_error, e);
      return ResultGenerator.genFailResult(DocConstant.update_error);
    }
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public RestResponse<String> delete(String id){
    try {
      advPrayLifePlaceMapper.deleteById(id);
      targetPositionMapper.deleteByRefId(id);
      return ResultGenerator.genSuccessResult(DocConstant.delete_success);
    } catch (Exception e) {
      log.error(DocConstant.delete_error, e);
      return ResultGenerator.genFailResult(DocConstant.delete_error);
    }
  }

  @Override
  public AdvPrayLifePlaceVo getByIdVo(String id) {
    return advPrayLifePlaceMapper.selectByIdVo(id);
  }

  @Override
  public List<AdvPrayLifePlaceVo> listTreeArea() {
    return RecursiveAddress(advPrayLifePlaceMapper.listTreeArea());
  }

  @Override
  public List<AdvPrayLifePlaceVo> listAllAddress() {
    //获取所有的地址数据
    return advPrayLifePlaceMapper.listAllAddress();
  }

  /**
   * 传入全查询的数据
   *
   * @param treeNodes
   * @return
   */
  private List<AdvPrayLifePlaceVo> RecursiveAddress(List<AdvPrayLifePlaceVo> treeNodes) {
    List<AdvPrayLifePlaceVo> trees = new ArrayList<AdvPrayLifePlaceVo>();
    for (AdvPrayLifePlaceVo treeNode : treeNodes) {
      //如果parentId为0或者2时说明在省市级别,说明有子节点继续查找子节点
      if ("2".equals(treeNode.getParentId()) || "0".equals(treeNode.getParentId())) {
        trees.add(findAddressChildren(treeNode, treeNodes));
      }
    }
    return trees;
  }

  /**
   * 递归查找地址子节点 
   *
   *  @param treeNodes 
   *  @return 
   *      
   */
  private AdvPrayLifePlaceVo findAddressChildren(AdvPrayLifePlaceVo treeNode, List<AdvPrayLifePlaceVo> treeNodes) {
    for (AdvPrayLifePlaceVo it : treeNodes) {
      if (treeNode.getId().equals(it.getParentId())) {
        //创建存放子节点的list
        if (treeNode.getChildrens() == null) {
          treeNode.setChildrens(new ArrayList<AdvPrayLifePlaceVo>());
        }
        //加上查找到的子节点
        treeNode.getChildrens().add(findAddressChildren(it, treeNodes));
      }
    }
    return treeNode;
  }

}