package com.xw.project.service.rescuetrain.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.RtmAedDto;
import com.xw.project.entity.TargetPosition;
import com.xw.project.mapper.RtmAedMapper;
import com.xw.project.mapper.TargetPositionMapper;
import com.xw.project.service.rescuetrain.RtmAedService;
import com.xw.project.entity.RtmAed;
import com.xw.project.vo.RtmAedVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * RtmAed服务类
 * </p>
 *
 * @author Jy
 * @since 2020-06-23
 */
@Service
public class RtmAedServiceImpl extends ServiceImpl<RtmAedMapper, RtmAed> implements RtmAedService {

    /**
     * aed表mapper
     */
    @Autowired
    private RtmAedMapper rtmAedMapper;

    /**
     * 基本表mapper
     */
    @Autowired
    private TargetPositionMapper targetPositionMapper;

    /**
     * 分页查询
     * @param rtmAedDto
     * @return
     */
    @Override
    public IPage<RtmAedVo> page(RtmAedDto rtmAedDto) {
        Page<RtmAedVo> pageInfo = new Page<>(rtmAedDto.getCurrent(), rtmAedDto.getSize());
        IPage<RtmAedVo> page = this.rtmAedMapper.selectPageVO(pageInfo,rtmAedDto);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResponse<String> add(RtmAedDto rtmAedDto) {
        RtmAed rtmAed = new RtmAed();
        TargetPosition targetPosition = new TargetPosition();
        //将前台数据复制到aed实体类中 rtmAedDto->rtmAed
        BeanUtils.copyProperties(rtmAedDto, rtmAed);
        //将前台数据复制到基本信息实体类中 rtmAedDto->targetPosition
        BeanUtils.copyProperties(rtmAedDto, targetPosition);
        try {
            rtmAedMapper.insert(rtmAed);
            //获取刚插入的主键ID
            targetPosition.setRefId(rtmAed.getId());
            targetPositionMapper.insert(targetPosition);
            return ResultGenerator.genSuccessResult(DocConstant.add_success);
        } catch (Exception e) {
            log.error(DocConstant.add_error, e);
            return ResultGenerator.genFailResult(DocConstant.add_error);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResponse<String> update(RtmAedDto rtmAedDto) {
        RtmAed rtmAed = new RtmAed();
        TargetPosition targetPosition = new TargetPosition();
        //将前台数据复制到aed实体类中 rtmAedDto->targetPosition
        BeanUtils.copyProperties(rtmAedDto, rtmAed);
        //将前台数据复制到基本信息实体类中 rtmAedDto->targetPosition
        BeanUtils.copyProperties(rtmAedDto, targetPosition);
        try {
            rtmAedMapper.updateById(rtmAed);
            targetPosition.setRefId(rtmAedDto.getId());
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
            rtmAedMapper.deleteById(id);
            targetPositionMapper.deleteByRefId(id);
            return ResultGenerator.genSuccessResult(DocConstant.delete_success);
        } catch (Exception e) {
            log.error(DocConstant.delete_error, e);
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
    }

    /**
     * 获取省市区的级联数据
     *
     * @return List<RtmAedVo>
     */
    @Override
    public List<RtmAedVo> listTreeArea() {
        return RecursiveAddress(rtmAedMapper.listTreeArea());
    }

    /**
     * 封装所有地址信息后返回到前端
     *
     * @return List<RtmAedVo>
     */
    @Override
    public List<RtmAedVo> listAllAddress() {
        //获取所有的地址数据
        return rtmAedMapper.listAllAddress();
    }

    /**
     * 获取AED详情
     *
     * @param id
     * @return 全查询数据
     */
    @Override
    public RtmAedVo getByIdVo(String id) {
        return rtmAedMapper.selectByIdVo(id);
    }

    /**
     * 传入全查询的数据
     *
     * @param treeNodes
     * @return
     */
    private List<RtmAedVo> RecursiveAddress(List<RtmAedVo> treeNodes) {
        List<RtmAedVo> trees = new ArrayList<RtmAedVo>();
        for (RtmAedVo treeNode : treeNodes) {
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
    private RtmAedVo findAddressChildren(RtmAedVo treeNode, List<RtmAedVo> treeNodes) {
        for (RtmAedVo it : treeNodes) {
            if (treeNode.getId().equals(it.getParentId())) {
                //创建存放子节点的list
                if (treeNode.getChildrens() == null) {
                    treeNode.setChildrens(new ArrayList<RtmAedVo>());
                }
                //加上查找到的子节点
                treeNode.getChildrens().add(findAddressChildren(it, treeNodes));
            }
        }
        return treeNode;
    }

}