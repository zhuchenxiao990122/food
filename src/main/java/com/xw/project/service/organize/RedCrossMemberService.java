package com.xw.project.service.organize;

import com.xw.common.result.RestResponse;
import com.xw.project.entity.RedCrossMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.project.entity.RedCrossMemberRecord;
import com.xw.project.vo.RedCrossMemberGroupVo;
import com.xw.project.vo.RedCrossMemberVo;
import com.xw.project.vo.RedCrossMemberTeenagersVo;

/**
 * <p>
 * 会员信息基本表 服务类
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-06
 */
public interface RedCrossMemberService extends IService<RedCrossMember> {

    RestResponse<String> addMemberAdult(RedCrossMemberVo redCrossMemberVo);

    RestResponse<String> addTeenagers(RedCrossMemberTeenagersVo redCrossMemberYoungVo);

    RestResponse<String> addGroup(RedCrossMemberGroupVo redCrossMemberGroupVo);

    RestResponse<String> memberTransfer(RedCrossMemberRecord redCrossMemberRecord);

    RestResponse<String> memberWithdraw(RedCrossMemberRecord redCrossMemberRecord);

    RestResponse<String> memberExpel(RedCrossMemberRecord redCrossMemberRecord);

}
