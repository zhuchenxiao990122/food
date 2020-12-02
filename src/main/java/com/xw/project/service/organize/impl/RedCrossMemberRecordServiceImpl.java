package com.xw.project.service.organize.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.core.constant.DocConstantSuper;
import com.xw.project.dto.ApproveDto;
import com.xw.project.dto.RedCrossMemberDto;
import com.xw.project.entity.RedCrossMember;
import com.xw.project.entity.RedCrossMemberRecord;
import com.xw.project.mapper.RedCrossMemberMapper;
import com.xw.project.mapper.RedCrossMemberRecordMapper;
import com.xw.project.service.organize.RedCrossMemberRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.project.vo.RedCrossMemberAdultVo;
import com.xw.project.vo.RedCrossMemberGroupVo;
import com.xw.project.vo.RedCrossMemberTeenagersVo;
import com.xw.project.vo.RedCrossMemberVo;
import io.swagger.models.auth.In;
import org.jcp.xml.dsig.internal.dom.DOMCanonicalizationMethod;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.css.DocumentCSS;

import java.util.List;

/**
 * <p>
 * 会员信息基本表 服务实现类
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-07
 */
@Service
public class RedCrossMemberRecordServiceImpl extends ServiceImpl<RedCrossMemberRecordMapper, RedCrossMemberRecord> implements RedCrossMemberRecordService {

    @Autowired
    private RedCrossMemberRecordMapper memberRecordMapper;
    @Autowired
    private RedCrossMemberMapper memberMapper;

    /**
     * 会员查询
     *
     * @param memberDto
     * @return
     */
    @Override
    public RestResponse<IPage<RedCrossMemberVo>> listApproveMember(RedCrossMemberDto memberDto) {
        Page<RedCrossMemberVo> page = new Page<>(memberDto.getCurrent(), memberDto.getSize());
        String memberType = memberDto.getMemberType();
        IPage<RedCrossMemberVo> redCrossMemberRecordIPage;

        if (DocConstantSuper.MEMBER_ADULT.equals(memberType)) {
            redCrossMemberRecordIPage = memberRecordMapper.listAdultMember(page, memberDto);
        } else if (DocConstantSuper.MEMBER_TEENAGER.equals(memberType)) {
            redCrossMemberRecordIPage = memberRecordMapper.listTeenagersMember(page, memberDto);
        } else if (DocConstantSuper.MEMBER_GROUP.equals(memberType)) {
            redCrossMemberRecordIPage = memberRecordMapper.listGroupMember(page, memberDto);
        } else {
            return null;
        }
        List<RedCrossMemberVo> records = redCrossMemberRecordIPage.getRecords();
        for (RedCrossMemberVo record : records) {
            if (record.getApprovalStatus().equals(DocConstantSuper.NOT_APPROVED)) {
                record.setApplyTypeName("未审批");
            } else if (record.getApprovalStatus().equals(DocConstantSuper.APPROVE_PASS)) {
                record.setApplyTypeName("审批通过");
            } else if (record.getApprovalStatus().equals(DocConstantSuper.NOT_APPROVE_PASS)) {
                record.setApplyTypeName("审批未通过");
            }

        }
        return ResultGenerator.genSuccessResult(redCrossMemberRecordIPage);
    }


    /**
     * 会员信息
     *
     * @param id
     * @param memberType
     * @return 1：成人会员
     * 2：青少年会员
     * 3：团体会员
     */
    @Override
    public RestResponse<Object> getMemberById(String id, String memberType) {
        if (DocConstantSuper.MEMBER_ADULT.equals(memberType)) {
            RedCrossMemberAdultVo memberAdultVo = new RedCrossMemberAdultVo();
            memberAdultVo = memberRecordMapper.getAdultMember(id);
            return ResultGenerator.genSuccessResult(memberAdultVo);
        } else if (DocConstantSuper.MEMBER_TEENAGER.equals(memberType)) {
            RedCrossMemberTeenagersVo memberTeenagersVo = new RedCrossMemberTeenagersVo();
            memberTeenagersVo = memberRecordMapper.getTeenagersMember(id);
            return ResultGenerator.genSuccessResult(memberTeenagersVo);
        } else {
            RedCrossMemberGroupVo memberGroupVo = new RedCrossMemberGroupVo();
            memberGroupVo = memberRecordMapper.getGroupMember(id);
            return ResultGenerator.genSuccessResult(memberGroupVo);
        }
    }


    /**
     * 入会审批
     *
     * @param redCrossMemberVo
     * @return 0001：入会审批成功 0010 入会审批失败
     * 001：正式会员
     */
    @Override
    @Transactional
    public RestResponse<String> approveRegisterMember(RedCrossMemberVo redCrossMemberVo) {

        RedCrossMemberRecord memberRecord = memberRecordMapper.selectById(redCrossMemberVo.getId());

        //"0" 审批成功
        if (DocConstantSuper.MEMBER_APPROVED.equals(redCrossMemberVo.getApprove())) {
            // 审批通过
            memberRecord.setApprovalStatus(DocConstantSuper.APPROVE_PASS);
        } else {
            //审批未通过
            memberRecord.setApprovalStatus(DocConstantSuper.NOT_APPROVE_PASS);
        }
        RedCrossMember member = new RedCrossMember();
        QueryWrapper<RedCrossMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RedCrossMember::getMemberCode, redCrossMemberVo.getMemberCode());
        RedCrossMember redCrossMember = memberMapper.selectOne(queryWrapper);
        try {
            if (redCrossMember == null) {
                BeanUtils.copyProperties(redCrossMemberVo, member);
                member.setId(null);
                //""
                if(DocConstantSuper.MEMBER_APPROVED.equals(redCrossMemberVo.getApprove())){
                    member.setApplyStatus(DocConstantSuper.APPROVE_PASS);
                }else {
                    member.setApplyStatus(DocConstantSuper.NOT_APPROVE_PASS);
                }
                member.setApplyDate(redCrossMemberVo.getApplyDate());
                memberMapper.insert(member);
            } else {
                if(DocConstantSuper.MEMBER_APPROVED.equals(redCrossMemberVo.getApprove())){
                    redCrossMember.setApplyStatus(DocConstantSuper.APPROVE_PASS);
                }else {
                    redCrossMember.setApplyStatus(DocConstantSuper.NOT_APPROVE_PASS);
                }
                memberMapper.updateById(redCrossMember);
            }
            memberRecordMapper.updateById(memberRecord);
            return ResultGenerator.genSuccessResult(DocConstant.member_approve_success);
        } catch (Exception e) {
            return ResultGenerator.genFailResult(DocConstant.member_approve_error);
        }
    }

    /**
     * 退会审批
     *
     * @param approveDto
     * @return 01 审批通过
     * 10 审批未通过
     */
    @Override
    @Transactional
    public RestResponse<String> approveWithdrawMember(ApproveDto approveDto) {
        String memberCode = approveDto.getMemberCode();
        QueryWrapper<RedCrossMember> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.lambda().eq(RedCrossMember::getMemberCode, memberCode).eq(RedCrossMember::getDelFlag, '0');
        RedCrossMemberRecord memberRecord = memberRecordMapper.selectById(approveDto.getId());
        String approve = approveDto.getApprove();
        if (DocConstantSuper.MEMBER_APPROVED.equals(approve)) {
            memberRecord.setApprovalStatus(DocConstantSuper.APPROVE_PASS);
        } else {
            memberRecord.setApprovalStatus(DocConstantSuper.NOT_APPROVE_PASS);
        }
        try {
            memberMapper.delete(memberQueryWrapper);
            memberRecordMapper.updateById(memberRecord);
            return ResultGenerator.genSuccessResult(DocConstant.member_approve_success);
        } catch (Exception e) {
            log.error(DocConstant.member_approve_error, e);
            return ResultGenerator.genFailResult(DocConstant.member_approve_error);
        }
    }

    /**
     * 除名审批
     *
     * @param approveDto
     * @return
     */
    @Override
    @Transactional
    public RestResponse<String> approveExpelMember(ApproveDto approveDto) {
        String memberCode = approveDto.getMemberCode();
        QueryWrapper<RedCrossMember> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.lambda().eq(RedCrossMember::getMemberCode, memberCode).eq(RedCrossMember::getDelFlag, '0');
        RedCrossMemberRecord memberRecord = memberRecordMapper.selectById(approveDto.getId());
        String approve = approveDto.getApprove();
        if (DocConstantSuper.MEMBER_APPROVED.equals(approve)) {
            memberRecord.setApprovalStatus(DocConstantSuper.APPROVE_PASS);
        } else {
            memberRecord.setApprovalStatus(DocConstantSuper.NOT_APPROVE_PASS);
        }
        try {
            memberMapper.delete(memberQueryWrapper);
            memberRecordMapper.updateById(memberRecord);
            return ResultGenerator.genSuccessResult(DocConstant.member_approve_success);
        } catch (Exception e) {
            log.error(DocConstant.member_approve_error, e);
            return ResultGenerator.genFailResult(DocConstant.member_approve_error);
        }
    }

    @Override
    public RestResponse<Integer> memberPendingCount(RedCrossMemberDto memberDto) {
        String applyType = memberDto.getApplyType() ;
        String memberType = memberDto.getMemberType();
        Integer count = memberRecordMapper.memberPendingCount(memberType, applyType);
        if (count != null) {
            return ResultGenerator.genSuccessResult(count);
        } else {
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
    }

    /**
     * 会员转会申请审批
     *
     * @param approveDto
     * @return
     */
    @Override
    @Transactional
    public RestResponse<String> approveTransferMember(ApproveDto approveDto) {
        String recordId = approveDto.getId();
        String memberCode = approveDto.getMemberCode();
        RedCrossMemberRecord memberRecord = memberRecordMapper.selectById(recordId);
        QueryWrapper<RedCrossMember> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.lambda().eq(RedCrossMember::getMemberCode, memberCode).eq(RedCrossMember::getDelFlag, "0");
        List<RedCrossMember> redCrossMembers = memberMapper.selectList(memberQueryWrapper);
        if (redCrossMembers.size() < 1) {
            return ResultGenerator.genFailResult(DocConstant.member_approve_success);
        }
        RedCrossMember member = redCrossMembers.get(0);
        //更新会员状态
        member.setOrgId(memberRecord.getIntoOrg());
        member.setApplyStatus(DocConstantSuper.NOT_APPROVED);
        //创建会员入会信息
        RedCrossMemberRecord redCrossMemberRecord = new RedCrossMemberRecord();
        BeanUtils.copyProperties(member, redCrossMemberRecord);
        redCrossMemberRecord.setApplyType(DocConstantSuper.MEMBER_APPLY_REGISTER);
        redCrossMemberRecord.setApprovalStatus(DocConstantSuper.NOT_APPROVED);
        redCrossMemberRecord.setId(null);
        if (DocConstantSuper.MEMBER_APPROVED.equals(approveDto.getApprove())) {
            memberRecord.setApprovalStatus(DocConstantSuper.APPROVE_PASS);
        } else {
            memberRecord.setApprovalStatus(DocConstantSuper.NOT_APPROVE_PASS);
        }
        try {
            memberMapper.updateById(member);
            memberRecordMapper.updateById(memberRecord);
            memberRecordMapper.insert(redCrossMemberRecord);
            return ResultGenerator.genSuccessResult(DocConstant.member_approve_success);
        } catch (Exception e) {
            log.error(DocConstant.member_approve_error, e);
            return ResultGenerator.genFailResult(DocConstant.member_approve_error);
        }
    }


}
