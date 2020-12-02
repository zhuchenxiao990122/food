package com.xw.project.service.organize;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.project.dto.ApproveDto;
import com.xw.project.dto.RedCrossMemberDto;
import com.xw.project.entity.RedCrossMemberRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.project.vo.RedCrossMemberVo;
import javafx.beans.binding.ObjectExpression;

import java.util.List;

/**
 * <p>
 * 会员信息基本表 服务类
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-07
 */
public interface RedCrossMemberRecordService extends IService<RedCrossMemberRecord> {

    RestResponse<IPage<RedCrossMemberVo>> listApproveMember(RedCrossMemberDto memberDto);

    RestResponse<Object> getMemberById(String id, String memberType);

    RestResponse<String> approveRegisterMember(RedCrossMemberVo redCrossMemberVo);

    RestResponse<String> approveWithdrawMember(ApproveDto approveDto);

    RestResponse<String> approveExpelMember(ApproveDto approveDto);

    RestResponse<Integer> memberPendingCount(RedCrossMemberDto memberDto);

    RestResponse<String> approveTransferMember(ApproveDto approveDto);
}
