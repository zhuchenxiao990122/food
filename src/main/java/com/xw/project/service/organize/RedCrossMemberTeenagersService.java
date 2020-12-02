package com.xw.project.service.organize;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.project.dto.RedCrossMemberDto;
import com.xw.project.entity.RedCrossMemberTeenagers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.project.vo.RedCrossMemberTeenagersVo;

/**
 * <p>
 * 青少年会员 服务类
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-06
 */
public interface RedCrossMemberTeenagersService extends IService<RedCrossMemberTeenagers> {

    RestResponse<IPage<RedCrossMemberTeenagersVo>> listRedCrossMemberYoung(RedCrossMemberDto redCrossMemberDto);

    RestResponse<RedCrossMemberTeenagersVo> getCrossMemberTeenagers(String memberCode);
}
