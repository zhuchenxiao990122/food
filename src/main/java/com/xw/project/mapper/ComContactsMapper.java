package com.xw.project.mapper;

import com.xw.project.entity.ComContacts;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.ComContactsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuli
 * @since 2020-04-28
 */
public interface ComContactsMapper extends BaseMapper<ComContacts> {

    List<ComContactsVo> listManufacturer();
}
