package com.xw.project.mapper;

import com.xw.project.entity.DisContact;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.DisContactVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuli
 * @since 2020-04-20
 */
public interface DisContactMapper extends BaseMapper<DisContact> {

    List<DisContactVo> getListByContactId(String id);
}
