package com.xw.project.service.disaster.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.activiti.service.ActivitiService;
import com.xw.common.entity.User;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.DisDonationProjectDto;
import com.xw.project.entity.DisDonationProject;
import com.xw.project.entity.DisDonationProjectDetail;
import com.xw.project.mapper.DisDonationProjectMapper;
import com.xw.project.service.common.ComFileService;
import com.xw.project.service.disaster.DisDonationProjectDetailService;
import com.xw.project.service.disaster.DisDonationProjectService;
import com.xw.project.vo.DisDonationProjectVo;
import com.xw.system.entity.Organize;
import com.xw.system.mapper.OrganizeMapper;
import com.xw.system.service.BizItemProcService;
import com.xw.system.util.AppUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * DisDonationProject服务类
 * </p>
 *
 * @author yuli
 * @since 2020-06-24
 */
@Service
public class DisDonationProjectServiceImpl extends ServiceImpl<DisDonationProjectMapper, DisDonationProject> implements DisDonationProjectService {
    @Autowired
    private BizItemProcService bizItemProcService;
    @Autowired
    private ActivitiService activitiService;
    @Autowired
    private OrganizeMapper organizeMapper;
    @Autowired
    private ComFileService comFileService;
    @Autowired
    private DisDonationProjectDetailService disDonationProjectDetailService;
    @Autowired
    private AppUtil appUtil;

    @Override
    public IPage<DisDonationProjectVo> page(DisDonationProjectDto disDonationProjectDto) {
        IPage<DisDonationProjectVo> page = new Page<>(disDonationProjectDto.getCurrent(), disDonationProjectDto.getSize());
        //获取物资信息
        IPage<DisDonationProjectVo> disDonationProjectIPage = baseMapper.pageApply(page, disDonationProjectDto);

        List<DisDonationProjectVo> records = disDonationProjectIPage.getRecords();
        for (DisDonationProjectVo disDonationProjectVo : records) {
            //调用物资查询接口，查询物资
            List<DisDonationProjectDetail> disDonationProjectDetails = disDonationProjectDetailService.search(disDonationProjectVo.getId());
            //将物资信息插入
            disDonationProjectVo.setDisDonationProjectDetails(disDonationProjectDetails);
            //插入文件
            List<String> fileIds = comFileService.search(disDonationProjectVo.getId());
            disDonationProjectVo.setFileIds(fileIds);
        }
        return disDonationProjectIPage;
    }

    @Override
    public RestResponse add(DisDonationProject disDonationProject) {
        //判断数据库中是否存在
        DisDonationProject donationProject = baseMapper.selectById(disDonationProject.getId());
        //获取当前登陆人id以及orgId
        User user = appUtil.getUser();
        String userId = user.getId();
        //存在则修改，否则新增
        try {
            if (null == donationProject) {
                disDonationProject.setApplyUser(userId);
                //待申请状态
                disDonationProject.setState(DocConstant.project_status_before);
                baseMapper.insert(disDonationProject);
            } else {
                baseMapper.updateById(disDonationProject);
            }
        } catch (Exception e) {
            log.error(DocConstant.add_error, e);
            return ResultGenerator.genFailResult(DocConstant.add_error);
        }
        return ResultGenerator.genSuccessResult(DocConstant.add_success);
    }

    @Override
    public void update(DisDonationProject disDonationProject) {
        baseMapper.updateById(disDonationProject);
    }

    @Override
    public RestResponse<String> delete(String id) {
        baseMapper.deleteById(id);
        return ResultGenerator.genSuccessResult(DocConstant.delete_success);
    }

    @Override
    @Transactional
    public RestResponse<String> applyStart(String id) {
        //1.获取当前登陆人id以及orgId
        User user = appUtil.getUser();
        String orgId = user.getUserOrg();
        String userId = user.getId();
        //2.工作流保存变量，根据条件查询哪些
        Map<String, Object> variables = new HashMap<>();
        variables.put("orgId", orgId);

        DisDonationProject disDonationProject = baseMapper.selectById(id);
        if (null != disDonationProject) {
            disDonationProject.setApplyUser(userId);
            disDonationProject.setApplyTime(new Date());
            disDonationProject.setState(DocConstant.project_status_apply);
            baseMapper.updateById(disDonationProject);
        } else {
            return ResultGenerator.genFailResult(DocConstant.add_error);
        }
        try {
            //3.查找当前流程是否控制
            String processDefKeyByBizItemId = bizItemProcService.getProcessDefKeyByBizItemId(DocConstant.ACTIVIT_DONATION_PROJECT_APPLY);
            //4.创建id
            Organize organize = organizeMapper.selectById(orgId);
            //5.启动流程
            activitiService.doStartAndComplete(userId, id, variables, processDefKeyByBizItemId);
        } catch (Exception e) {
            return ResultGenerator.genFailResult(DocConstant.add_error);
        }
        return ResultGenerator.genSuccessResult(DocConstant.add_success);
    }

    @Override
    public IPage<DisDonationProjectVo> pageApprovalTask(int current, int size) {
        //1.获取当前登陆人id以及orgId
        User user = appUtil.getUser();
        String orgId = user.getUserOrg();
        String userId = user.getId();
        Map<String, Object> variables = new HashMap<>();
        variables.put("orgId", orgId);
        //2查找当前流程是否控制
        String processDefKeyByBizItemId = bizItemProcService.getProcessDefKeyByBizItemId(DocConstant.ACTIVIT_DONATION_PROJECT_APPLY);
        int total = activitiService.totalTodo(userId, variables, processDefKeyByBizItemId);
        List<Map<String, String>> list = activitiService.pageTodo(userId, variables, processDefKeyByBizItemId, (current - 1) * size, size);
        //3.获取业务主键集合
        List<DisDonationProject> disDonationProjects = new ArrayList<>();
        List<String> ids = list.stream().map(i -> {
            return i.get("businessKey");
        }).collect(Collectors.toList());
        if (ids != null && ids.size() > 0) {
            disDonationProjects = baseMapper.selectBatchIds(ids);
        }
        //重新封装disDonationProjects，以便于插入物资信息以及文件信息
        List<DisDonationProjectVo> result = new ArrayList<>();
        for (DisDonationProject disDonationProject : disDonationProjects) {
            DisDonationProjectVo disDonationProjectVo = new DisDonationProjectVo();
            BeanUtils.copyProperties(disDonationProject, disDonationProjectVo);
            //插入物资信息
            List<DisDonationProjectDetail> disDonationProjectDetails = disDonationProjectDetailService.search(disDonationProjectVo.getId());
            //将物资信息插入
            disDonationProjectVo.setDisDonationProjectDetails(disDonationProjectDetails);
            //插入文件信息
            List<String> fileIds = comFileService.search(disDonationProjectVo.getId());
            disDonationProjectVo.setFileIds(fileIds);

            result.add(disDonationProjectVo);
        }
        //将分页信息重新封装
        IPage<DisDonationProjectVo> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        page.setTotal(total);
        page.setRecords(result);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public RestResponse approval(String id, String state, String comment) {
        //1.获取当前登陆人id以及orgId
        User user = appUtil.getUser();
        String userId = user.getId();
        String orgId = user.getUserOrg();
        DisDonationProject disDonationProject = baseMapper.selectById(id);
        if (null != disDonationProject) {
            disDonationProject.setState(state);
            try {
                //2.更新当前业务表信息
                baseMapper.updateById(disDonationProject);
                //3.保存审批信息
                activitiService.complete(id, comment, userId, orgId);
            } catch (Exception e) {
                return ResultGenerator.genFailResult(DocConstant.add_error);
            }
            return ResultGenerator.genSuccessResult(DocConstant.add_success);
        } else {
            return ResultGenerator.genFailResult(DocConstant.add_error);
        }
    }

    @Override
    public IPage<DisDonationProjectVo> pagePublish(int current, int size) {
        IPage<DisDonationProject> page = new Page<>(current, size);
     /*   Map map=new HashMap();
        map.put("",);
        map.put("",);*/
        IPage<DisDonationProjectVo> disDonationProjectVoIPage = baseMapper.pagePublish(page);
        List<DisDonationProjectVo> records = disDonationProjectVoIPage.getRecords();
        for (DisDonationProjectVo disDonationProjectVo : records) {
            //调用物资查询接口，查询物资
            List<DisDonationProjectDetail> disDonationProjectDetails = disDonationProjectDetailService.search(disDonationProjectVo.getId());
            disDonationProjectVo.setDisDonationProjectDetails(disDonationProjectDetails);
            //插入文件
            List<String> fileIds = comFileService.search(disDonationProjectVo.getId());
            disDonationProjectVo.setFileIds(fileIds);
        }
        return disDonationProjectVoIPage;
    }


    @Override
    public RestResponse<String> projectPublish(String id) {
        DisDonationProject disDonationProject = baseMapper.selectById(id);
        if (null != disDonationProject) {
            disDonationProject.setState(DocConstant.project_status_publish);
            disDonationProject.setPublishTime(new Date());
            try {
                baseMapper.updateById(disDonationProject);

            } catch (Exception e) {
                return ResultGenerator.genFailResult(DocConstant.publish_error);
            }
        } else {
            return ResultGenerator.genFailResult(DocConstant.publish_error);
        }
        return ResultGenerator.genSuccessResult(DocConstant.publish_success);
    }

    @Override
    public RestResponse<IPage<DisDonationProject>> cancelProject(String id) {
        {
            DisDonationProject disDonationProject = baseMapper.selectById(id);
            if (null != disDonationProject) {
                disDonationProject.setState(DocConstant.project_status_canel);
                disDonationProject.setCancelTime(new Date());
                try {
                    baseMapper.updateById(disDonationProject);
                } catch (Exception e) {
                    return ResultGenerator.genFailResult(DocConstant.canel_error);
                }
            } else {
                return ResultGenerator.genFailResult(DocConstant.canel_error);
            }
            return ResultGenerator.genSuccessResult(DocConstant.canel_success);
        }
    }

    @Override
    public IPage<DisDonationProjectVo> pageApply(DisDonationProjectDto disDonationProjectDto) {
        User user = appUtil.getUser();
        String userId = user.getId();
        disDonationProjectDto.setUserId(userId);
        IPage<DisDonationProjectVo> page = new Page<>(disDonationProjectDto.getCurrent(), disDonationProjectDto.getSize());
        IPage<DisDonationProjectVo> disDonationProjectVoIPage = baseMapper.pageApply(page, disDonationProjectDto);
        List<DisDonationProjectVo> records = disDonationProjectVoIPage.getRecords();
        for (DisDonationProjectVo disDonationProjectVo : records) {
            //插入物资信息
            List<DisDonationProjectDetail> disDonationProjectDetails = disDonationProjectDetailService.search(disDonationProjectVo.getId());
            disDonationProjectVo.setDisDonationProjectDetails(disDonationProjectDetails);
            //插入文件信息
            List<String> fileIds = comFileService.search(disDonationProjectVo.getId());
            disDonationProjectVo.setFileIds(fileIds);
        }
        return disDonationProjectVoIPage;
    }

    @Override
    public List<Map<String, String>> fundList(String orgId) {
        Map<String, String> map = new HashMap<>();
        map.put("orgId", orgId);
        //选择已发布的
        map.put("state", DocConstant.project_status_publish);
        //刷选出不能为捐赠物资的
        map.put("type", DocConstant.donation_material);
        return baseMapper.selectListByMap(map);
    }

    @Override
    public List<Map<String, String>> materialList(String orgId) {
        Map<String, String> map = new HashMap<>();
        map.put("orgId", orgId);
        //选择已发布的
        map.put("state", DocConstant.project_status_publish);
        //刷选出不能为捐赠物资的
        map.put("type", DocConstant.donation_fund);
        return baseMapper.selectListByMap(map);
    }

}