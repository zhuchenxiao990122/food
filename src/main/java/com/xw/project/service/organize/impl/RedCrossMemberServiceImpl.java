package com.xw.project.service.organize.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.StringUtil;
import com.xw.core.constant.DocConstant;
import com.xw.core.constant.DocConstantSuper;
import com.xw.project.entity.*;
import com.xw.project.mapper.*;
import com.xw.project.service.organize.RedCrossMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.project.vo.RedCrossMemberGroupVo;
import com.xw.project.vo.RedCrossMemberVo;
import com.xw.project.vo.RedCrossMemberTeenagersVo;
import com.xw.system.entity.Organize;
import com.xw.system.mapper.OrganizeMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>
 * 会员信息基本表 服务实现类
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-06
 */
@Service
public class RedCrossMemberServiceImpl extends ServiceImpl<RedCrossMemberMapper, RedCrossMember> implements RedCrossMemberService {


    @Autowired
    private RedCrossMemberMapper memberMapper;
    @Autowired
    private RedCrossMemberAdultMapper memberAdultMapper;
    @Autowired
    private OrganizeMapper organizeMapper;
    @Autowired
    private RedCrossMemberTeenagersMapper memberTeenagersMapper;
    @Autowired
    private RedCrossMemberGroupMapper memberGroupMapper;
    @Autowired
    private RedCrossMemberRecordMapper memberRecordMapper;

    /**
     * 成人会员入会申请
     * @param redCrossMemberVo
     * @return
     * 0000：入会申请
     */
    @Transactional
    @Override
    public RestResponse<String> addMemberAdult(RedCrossMemberVo redCrossMemberVo) {
        RedCrossMemberAdult redCrossMemberAdult = new RedCrossMemberAdult();
        RedCrossMemberRecord redCrossMemberRecord = new RedCrossMemberRecord();
        BeanUtils.copyProperties(redCrossMemberVo, redCrossMemberAdult);
        BeanUtils.copyProperties(redCrossMemberVo,redCrossMemberRecord);
        String memberCode = generateMemberCode(redCrossMemberVo.getOrgId(), redCrossMemberVo.getMemberType());
        redCrossMemberAdult.setMemberId(memberCode);
        redCrossMemberRecord.setApplyType(DocConstantSuper.MEMBER_APPLY_REGISTER);
        redCrossMemberRecord.setApprovalStatus(DocConstantSuper.NOT_APPROVED);
        redCrossMemberRecord.setMemberCode(memberCode);
        try {
            memberAdultMapper.insert(redCrossMemberAdult);
            memberRecordMapper.insert(redCrossMemberRecord);
            return ResultGenerator.genSuccessResult(DocConstant.application_submissions_success);
        } catch (Exception e) {
            log.error(DocConstant.application_submissions_error, e);
            return ResultGenerator.genFailResult(DocConstant.application_submissions_error);
        }
    }

    /**
     * 青少年入会申请
     * @param redCrossMemberYoungVo
     * @return
     * 0000：入会申请
     */
    @Override
    @Transactional
    public RestResponse<String> addTeenagers(RedCrossMemberTeenagersVo redCrossMemberYoungVo) {
        RedCrossMemberTeenagers memberYoung = new RedCrossMemberTeenagers();
        RedCrossMemberRecord memberRecord = new RedCrossMemberRecord();
        BeanUtils.copyProperties(redCrossMemberYoungVo,memberYoung);
        BeanUtils.copyProperties(redCrossMemberYoungVo,memberRecord);
        String memberCode = generateMemberCode(redCrossMemberYoungVo.getOrgId(), redCrossMemberYoungVo.getMemberType());
        memberYoung.setMemberId(memberCode);
        memberRecord.setApplyType(DocConstantSuper.MEMBER_APPLY_REGISTER);
        memberRecord.setApprovalStatus(DocConstantSuper.NOT_APPROVED);
        memberRecord.setMemberCode(memberCode);
        try{
            memberTeenagersMapper.insert(memberYoung);
            memberRecordMapper.insert(memberRecord);
            return ResultGenerator.genSuccessResult(DocConstant.application_submissions_success);
        }catch (Exception e){
            log.error(DocConstant.application_submissions_error,e);
            return ResultGenerator.genFailResult(DocConstant.application_submissions_error);
        }
    }

    /**
     * 团体会员入会申请
     * @param redCrossMemberGroupVo
     * @return
     * 0000：入会申请
     */
    @Override
    @Transactional
    public RestResponse<String> addGroup(RedCrossMemberGroupVo redCrossMemberGroupVo) {
        RedCrossMemberGroup memberGroup = new RedCrossMemberGroup();
        RedCrossMemberRecord memberRecord = new RedCrossMemberRecord();
        BeanUtils.copyProperties(redCrossMemberGroupVo,memberGroup);
        BeanUtils.copyProperties(redCrossMemberGroupVo,memberRecord);
        String memberCode = generateMemberCode(redCrossMemberGroupVo.getOrgId(), redCrossMemberGroupVo.getMemberType());
        memberGroup.setMemberId(memberCode);
        memberRecord.setApplyType(DocConstantSuper.MEMBER_APPLY_REGISTER);
        memberRecord.setApprovalStatus(DocConstantSuper.NOT_APPROVED);
        memberRecord.setMemberCode(memberCode);
        try{
            memberGroupMapper.insert(memberGroup);
            memberRecordMapper.insert(memberRecord);
            return ResultGenerator.genSuccessResult(DocConstant.application_submissions_success);
        }catch (Exception e){
            log.error(DocConstant.application_submissions_error,e);
            return ResultGenerator.genFailResult(DocConstant.application_submissions_error);
        }
    }

    /**
     * 转会申请
     * @param redCrossMemberRecord
     * @return
     * 0100：转会申请
     */
    @Override
    @Transactional
    public RestResponse<String> memberTransfer(RedCrossMemberRecord redCrossMemberRecord) {
        RedCrossMember redCrossMember = new RedCrossMember();
        BeanUtils.copyProperties(redCrossMemberRecord,redCrossMember);
        redCrossMemberRecord.setApplyType(DocConstantSuper.MEMBER_APPLY_TRANSFER);
        redCrossMemberRecord.setApprovalStatus(DocConstantSuper.NOT_APPROVED);
        redCrossMember.setApplyStatus(DocConstantSuper.NOT_APPROVED);
        redCrossMemberRecord.setId(null);
        try{
            memberMapper.updateById(redCrossMember);
            memberRecordMapper.insert(redCrossMemberRecord);
            return ResultGenerator.genSuccessResult(DocConstant.application_submissions_success);
        }catch (Exception e){
            log.error(DocConstant.application_submissions_error,e);
            return ResultGenerator.genFailResult(DocConstant.application_submissions_error);
        }
    }

    /**
     * 退会申请
     * @param redCrossMemberRecord
     * @return
     */
    @Override
    @Transactional
    public RestResponse<String> memberWithdraw(RedCrossMemberRecord redCrossMemberRecord) {

        RedCrossMember redCrossMember = new RedCrossMember();
        BeanUtils.copyProperties(redCrossMemberRecord,redCrossMember);
        redCrossMemberRecord.setApplyType(DocConstantSuper.MEMBER_APPLY_WITHDRAW);
        redCrossMemberRecord.setApprovalStatus(DocConstantSuper.NOT_APPROVED);
        redCrossMember.setApplyStatus(DocConstantSuper.NOT_APPROVED);
        redCrossMemberRecord.setId(null);
        try{
            memberMapper.updateById(redCrossMember);
            memberRecordMapper.insert(redCrossMemberRecord);
            return ResultGenerator.genSuccessResult(DocConstant.application_submissions_success);
        }catch (Exception e){
            log.error(DocConstant.application_submissions_error,e);
            return ResultGenerator.genFailResult(DocConstant.application_submissions_error);
        }
    }

    /**
     * 除名申请
     * @param redCrossMemberRecord
     * @return
     * 1100：除名申请
     */
    @Override
    @Transactional
    public RestResponse<String> memberExpel(RedCrossMemberRecord redCrossMemberRecord) {

        RedCrossMember redCrossMember = new RedCrossMember();
        BeanUtils.copyProperties(redCrossMemberRecord,redCrossMember);
        redCrossMemberRecord.setApplyType(DocConstantSuper.MEMBER_APPLY_EXPEL);
        redCrossMemberRecord.setApprovalStatus(DocConstantSuper.NOT_APPROVED);
        redCrossMember.setApplyStatus(DocConstantSuper.NOT_APPROVED);
        redCrossMemberRecord.setId(null);
        try{
            memberMapper.updateById(redCrossMember);
            memberRecordMapper.insert(redCrossMemberRecord);
            return ResultGenerator.genSuccessResult(DocConstant.application_submissions_success);
        }catch (Exception e){
            log.error(DocConstant.application_submissions_error,e);
            return ResultGenerator.genFailResult(DocConstant.application_submissions_error);
        }
    }

    /**
     * 会员编号生成
     *
     * @param orgId
     * @param memberType
     * @return
     */
    public String generateMemberCode(@NotEmpty String orgId, @NotEmpty String memberType) {
        Organize organize = organizeMapper.selectById(orgId);
        String substring;
        if(organize.getOrgcoding().length()>10){
            substring = organize.getOrgcoding().substring(0, 10);
        }else {
            substring = organize.getOrgcoding();
        }
        String join = StringUtil.join(StringUtil.join(substring, memberType), memberType);
        QueryWrapper<RedCrossMemberRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().likeRight(RedCrossMemberRecord::getMemberCode, join).orderByDesc(RedCrossMemberRecord::getMemberCode).last("limit 0,1");
        List<RedCrossMemberRecord> redCrossMemberRecords = memberRecordMapper.selectList(queryWrapper);
        String memberCode = "";
        if (redCrossMemberRecords != null && redCrossMemberRecords.size() > 0) {
            RedCrossMemberRecord redCrossMember = redCrossMemberRecords.get(0);
            Long codeLong= Long.valueOf(redCrossMember.getMemberCode().substring(11));
            Long aLong = codeLong+ 1;
            String memberCd= String.valueOf(aLong).substring(1);
            memberCode =join+ memberCd;

        } else {
            memberCode = join + "00000001";
        }
        return memberCode;
    }


}
