package com.xw.project.service.other;

import com.xw.project.vo.MeritListConvertVo;

/**
 * 功能描述：
 *
 * @Author: 喻礼
 * @Date: 2020/3/5 11:42
 */
public interface MeritListConvertService {
    MeritListConvertVo convert(int first, int size, String donor, String id) throws Exception;
}

