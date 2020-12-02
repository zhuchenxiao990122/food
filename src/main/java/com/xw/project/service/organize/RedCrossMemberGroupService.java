package com.xw.project.service.organize;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.project.dto.RedCrossMemberDto;
import com.xw.project.entity.RedCrossMemberGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.project.vo.RedCrossMemberAdultVo;
import com.xw.project.vo.RedCrossMemberGroupVo;

/**
 * <p>
 * 团体会员 服务类
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-06
 */
public interface RedCrossMemberGroupService extends IService<RedCrossMemberGroup> {

    RestResponse<IPage<RedCrossMemberAdultVo>> listRedCrossMemberGroup(RedCrossMemberDto redCrossMemberDto);

    RestResponse<RedCrossMemberGroupVo> getCrossMemberGroup(String memberCode);
}
