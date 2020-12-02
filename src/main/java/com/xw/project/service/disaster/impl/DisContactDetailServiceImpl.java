package com.xw.project.service.disaster.impl;

import com.xw.project.entity.DisContactDetail;
import com.xw.project.mapper.DisContactDetailMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.project.service.disaster.DisContactDetailService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuli
 * @since 2020-04-20
 */
@Service
public class DisContactDetailServiceImpl extends ServiceImpl<DisContactDetailMapper, DisContactDetail> implements DisContactDetailService {

    @Override
    @Async
    public void test(int i) throws InterruptedException {
        System.out.println("开始");
        Thread.sleep(10);
        System.out.println("线程" + Thread.currentThread().getName() + " 执行异步任务：" + i);

    }
}
