package com.xw.project.service.common.impl;

import com.xw.project.entity.ComContacts;
import com.xw.project.mapper.ComContactsMapper;
import com.xw.project.service.common.ComContactsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.project.vo.ComContactsVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yuli
 * @since 2020-04-28
 */
@Service
public class ComContactsServiceImpl extends ServiceImpl<ComContactsMapper, ComContacts> implements ComContactsService {

    @Override
    public List<ComContactsVo> listManufacturer() {
        return baseMapper.listManufacturer();
    }
}
