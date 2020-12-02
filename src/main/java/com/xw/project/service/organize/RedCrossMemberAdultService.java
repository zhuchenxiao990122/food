package com.xw.project.service.organize;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.project.dto.RedCrossMemberDto;
import com.xw.project.entity.RedCrossMemberAdult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.project.entity.RedCrossMemberRecord;
import com.xw.project.vo.RedCrossMemberAdultVo;
import com.xw.project.vo.RedCrossMemberVo;

import java.util.List;

/**
 * <p>
 * adult_member 成人会员表 服务类
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-06
 */
public interface RedCrossMemberAdultService extends IService<RedCrossMemberAdult> {

    RestResponse<IPage<RedCrossMemberAdultVo>> listRedCrossMemberAdult(RedCrossMemberDto redCrossMemberDto);

    RestResponse<RedCrossMemberVo> getCrossMemberAdult(String memberCode);

}
