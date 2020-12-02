package com.xw.project.service.disaster;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.project.entity.DisContact;
import com.xw.project.vo.DisContactVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuli
 * @since 2020-04-20
 */
public interface DisContactService extends IService<DisContact> {

    List<DisContactVo> getListByContactId(String id);
}
