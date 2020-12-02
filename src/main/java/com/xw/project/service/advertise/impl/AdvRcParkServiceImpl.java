package com.xw.project.service.advertise.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.AdvRcParkDto;
import com.xw.project.entity.TargetPosition;
import com.xw.project.mapper.AdvRcParkMapper;
import com.xw.project.mapper.TargetPositionMapper;
import com.xw.project.service.advertise.AdvRcParkService;
import com.xw.project.entity.AdvRcPark;

import com.xw.project.vo.AdvRcParkVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * AdvRcPark服务类
 * </p>
 *
 * @author Jy
 * @since 2020-07-03
 */
@Service
public class AdvRcParkServiceImpl extends ServiceImpl<AdvRcParkMapper, AdvRcPark> implements AdvRcParkService {

    @Autowired
    private AdvRcParkMapper advRcParkMapper;

    @Autowired
    private TargetPositionMapper targetPositionMapper;

    @Override
    public IPage<AdvRcParkVo> page(AdvRcParkDto advRcParkDto) {
        IPage<AdvRcParkVo> pageInfo = new Page<>(advRcParkDto.getCurrent(), advRcParkDto.getSize());
        IPage<AdvRcParkVo> page = this.advRcParkMapper.selectPageVO(pageInfo, advRcParkDto);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResponse<String> add(AdvRcParkDto advRcParkDto) {
        AdvRcPark advRcPark = new AdvRcPark();
        TargetPosition targetPosition = new TargetPosition();
        //将前台数据复制到实体类中 advRcParkDto->advRcPark
        BeanUtils.copyProperties(advRcParkDto, advRcPark);
        //将前台数据复制到基本信息实体类中 rtmAedDto->targetPosition
        BeanUtils.copyProperties(advRcParkDto, targetPosition);
        try {
            advRcParkMapper.insert(advRcPark);
            //获取刚插入的主键ID
            targetPosition.setRefId(advRcPark.getId());
            targetPositionMapper.insert(targetPosition);
            return ResultGenerator.genSuccessResult(DocConstant.add_success);
        } catch (Exception e) {
            log.error(DocConstant.add_error, e);
            return ResultGenerator.genFailResult(DocConstant.add_error);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResponse<String> update(AdvRcParkDto advRcParkDto) {
        AdvRcPark advRcPark = new AdvRcPark();
        TargetPosition targetPosition = new TargetPosition();
        //将前台数据复制到实体类中 advRcParkDto->advRcPark
        BeanUtils.copyProperties(advRcParkDto, advRcPark);
        //将前台数据复制到基本信息实体类中 rtmAedDto->targetPosition
        BeanUtils.copyProperties(advRcParkDto, targetPosition);
        try {
            advRcParkMapper.updateById(advRcPark);
            targetPosition.setRefId(advRcParkDto.getId());
            targetPositionMapper.updateByRefId(targetPosition);
            return ResultGenerator.genSuccessResult(DocConstant.update_success);
        } catch (Exception e) {
            log.error(DocConstant.update_error, e);
            return ResultGenerator.genFailResult(DocConstant.update_error);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResponse<String> delete(String id) {
        try {
            advRcParkMapper.deleteById(id);
            targetPositionMapper.deleteByRefId(id);
            return ResultGenerator.genSuccessResult(DocConstant.delete_success);
        } catch (Exception e) {
            log.error(DocConstant.delete_error, e);
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
    }

    @Override
    public AdvRcParkVo getByIdVo(String id) {
        return advRcParkMapper.getByIdVo(id);
    }

    /**
     * 获取省市区的级联数据
     *
     * @return List<AdvRcParkVo>
     */
    @Override
    public List<AdvRcParkVo> listTreeArea() {
        return RecursiveAddress(advRcParkMapper.listTreeArea());
    }

    /**
     * 封装所有地址信息后返回到前端
     *
     * @return List<AdvRcParkVo>
     */
    @Override
    public List<AdvRcParkVo> listAllAddress() {
        //获取所有的地址数据
        return advRcParkMapper.listAllAddress();
    }

    /**
     * 传入全查询的数据
     *
     * @param treeNodes
     * @return
     */
    private List<AdvRcParkVo> RecursiveAddress(List<AdvRcParkVo> treeNodes) {
        List<AdvRcParkVo> trees = new ArrayList<AdvRcParkVo>();
        for (AdvRcParkVo treeNode : treeNodes) {
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
     * @param treeNodes 
     * @return       
     */
    private AdvRcParkVo findAddressChildren(AdvRcParkVo treeNode, List<AdvRcParkVo> treeNodes) {
        for (AdvRcParkVo it : treeNodes) {
            if (treeNode.getId().equals(it.getParentId())) {
                //创建存放子节点的list
                if (treeNode.getChildrens() == null) {
                    treeNode.setChildrens(new ArrayList<AdvRcParkVo>());
                }
                //加上查找到的子节点
                treeNode.getChildrens().add(findAddressChildren(it, treeNodes));
            }
        }
        return treeNode;
    }

}