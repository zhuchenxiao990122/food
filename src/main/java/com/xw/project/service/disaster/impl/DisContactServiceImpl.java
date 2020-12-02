package com.xw.project.service.disaster.impl;

import com.xw.project.entity.DisContact;
import com.xw.project.mapper.DisContactMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.project.service.disaster.DisContactService;
import com.xw.project.vo.DisContactVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yuli
 * @since 2020-04-20
 */
@Service
public class DisContactServiceImpl extends ServiceImpl<DisContactMapper, DisContact> implements DisContactService {
    @Autowired
    private DisContactMapper disContactMapper;

    @Override
    @Async
    public List<DisContactVo> getListByContactId(String id) {
        return disContactMapper.getListByContactId(id);
    }
}
