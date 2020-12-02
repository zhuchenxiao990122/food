package com.xw.project.service.advertise.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.AdvEduLifeHallDto;
import com.xw.project.entity.TargetPosition;
import com.xw.project.mapper.AdvEduLifeHallMapper;
import com.xw.project.mapper.TargetPositionMapper;
import com.xw.project.service.advertise.AdvEduLifeHallService;
import com.xw.project.entity.AdvEduLifeHall;

import com.xw.project.vo.AdvEduLifeHallVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

/**
 * <p>
 * AdvEduLifeHall服务类
 * </p>
 *
 * @author Jy
 * @since 2020-06-30
 */
@Service
public class AdvEduLifeHallServiceImpl extends ServiceImpl<AdvEduLifeHallMapper, AdvEduLifeHall> implements AdvEduLifeHallService {

    @Autowired
    private AdvEduLifeHallMapper advEduLifeHallMapper;

    @Autowired
    private TargetPositionMapper targetPositionMapper;

    @Override
    public IPage<AdvEduLifeHallVo> page(AdvEduLifeHallDto advEduLifeHallDto) {
        IPage<AdvEduLifeHall> pageInfo = new Page<>(advEduLifeHallDto.getCurrent(), advEduLifeHallDto.getSize());
        IPage<AdvEduLifeHallVo> page = this.advEduLifeHallMapper.selectPageVO(pageInfo, advEduLifeHallDto);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResponse<String> add(AdvEduLifeHallDto advEduLifeHallDto) {
        AdvEduLifeHall advEduLifeHall = new AdvEduLifeHall();
        TargetPosition targetPosition = new TargetPosition();
        //将前台数据复制到信息表中 advEduLifeHallDto->advEduLifeHall
        BeanUtils.copyProperties(advEduLifeHallDto, advEduLifeHall);
        //将前台数据复制到基本信息表中 advEduLifeHallDto->targetPosition
        BeanUtils.copyProperties(advEduLifeHallDto, targetPosition);
        try {
            advEduLifeHallMapper.insert(advEduLifeHall);
            //获取刚插入的主键ID
            targetPosition.setRefId(advEduLifeHall.getId());
            targetPositionMapper.insert(targetPosition);
            return ResultGenerator.genSuccessResult(DocConstant.add_success);
        } catch (Exception e) {
            return ResultGenerator.genFailResult(DocConstant.add_error);
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResponse<String> update(AdvEduLifeHallDto advEduLifeHallDto) {
        AdvEduLifeHall advEduLifeHall = new AdvEduLifeHall();
        TargetPosition targetPosition = new TargetPosition();
        //将前台数据复制到aed信息表中 advEduLifeHallDto->advEduLifeHall
        BeanUtils.copyProperties(advEduLifeHallDto, advEduLifeHall);
        //将前台数据复制到基本信息表中 advEduLifeHallDto->targetPosition
        BeanUtils.copyProperties(advEduLifeHallDto, targetPosition);
        try {
            advEduLifeHallMapper.updateById(advEduLifeHall);
            targetPosition.setRefId(advEduLifeHallDto.getId());
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
            advEduLifeHallMapper.deleteById(id);
            targetPositionMapper.deleteByRefId(id);
            return ResultGenerator.genSuccessResult(DocConstant.delete_success);
        } catch (Exception e) {
            log.error(DocConstant.delete_error, e);
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
    }

    @Override
    public AdvEduLifeHallVo getByIdVo(String id) {
        return advEduLifeHallMapper.selectByIdVo(id);
    }

    @Override
    public List<AdvEduLifeHallVo> listTreeArea() {
        return RecursiveAddress(advEduLifeHallMapper.listTreeArea());
    }

    @Override
    public List<AdvEduLifeHallVo> listAllAddress() {
        //获取所有的地址数据
        return advEduLifeHallMapper.listAllAddress();
    }

    /**
     * 传入全查询的数据
     *
     * @param treeNodes
     * @return
     */
    private List<AdvEduLifeHallVo> RecursiveAddress(List<AdvEduLifeHallVo> treeNodes) {
        List<AdvEduLifeHallVo> trees = new ArrayList<AdvEduLifeHallVo>();
        for (AdvEduLifeHallVo treeNode : treeNodes) {
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
    private AdvEduLifeHallVo findAddressChildren(AdvEduLifeHallVo treeNode, List<AdvEduLifeHallVo> treeNodes) {
        for (AdvEduLifeHallVo it : treeNodes) {
            if (treeNode.getId().equals(it.getParentId())) {
                //创建存放子节点的list
                if (treeNode.getChildrens() == null) {
                    treeNode.setChildrens(new ArrayList<AdvEduLifeHallVo>());
                }
                //加上查找到的子节点
                treeNode.getChildrens().add(findAddressChildren(it, treeNodes));
            }
        }
        return treeNode;
    }

}