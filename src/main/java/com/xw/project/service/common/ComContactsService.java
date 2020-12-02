package com.xw.project.service.common;

import com.xw.project.entity.ComContacts;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.project.vo.ComContactsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuli
 * @since 2020-04-28
 */
public interface ComContactsService extends IService<ComContacts> {

    List<ComContactsVo> listManufacturer();
}
