package com.xw.project.service.disaster;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.common.result.RestResponse;
import com.xw.project.dto.DisRescueRequestDto;
import com.xw.project.entity.DisRescueRequest;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.project.vo.DisPublishVo;
import com.xw.project.vo.DisRescueRequestVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caoshuhao
 * @since 2020-06-28
 */
public interface DisRescueRequestService extends IService<DisRescueRequest> {

    RestResponse<IPage<DisRescueRequestVo>> search(DisRescueRequestDto disRescueRequestDto);

    String addDisRescueRequest(DisRescueRequestVo disRescueRequestVo);

    RestResponse<DisRescueRequestVo> getRescueRequest(String publishId, String applyNumber);

    RestResponse<List<String>> listPublishCode(String publishOrg);

    RestResponse<String> delete(String publishId, String applyNumber);
}
