package com.xw.project.service.disaster;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.project.dto.DisPublishDto;
import com.xw.project.entity.DisDisasterPublish;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.project.vo.DisPublishVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caoshuhao
 * @since 2020-06-23
 */
public interface DisDisasterPublishService extends IService<DisDisasterPublish> {

    RestResponse<IPage<DisPublishVo>> search(DisPublishDto disPublishDto);

    RestResponse<String> addDisasterPublish(DisPublishVo disPublishVo) ;

    RestResponse<DisPublishVo> getDisPublish(String id);

    RestResponse<String>  updateDisPublish(DisPublishVo disPublishVo);

    RestResponse<String>  deleteDisPublish(String id);
}
