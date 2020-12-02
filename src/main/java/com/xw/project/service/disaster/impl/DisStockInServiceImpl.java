package com.xw.project.service.disaster.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.common.util.UUIDGenerator;
import com.xw.project.entity.DisReserveNotice;
import com.xw.project.entity.DisReserveNoticeDetail;
import com.xw.project.entity.DisStockIn;
import com.xw.project.entity.DisStockInDetail;
import com.xw.project.mapper.DisReserveNoticeDetailMapper;
import com.xw.project.mapper.DisReserveNoticeMapper;
import com.xw.project.mapper.DisStockInDetailMapper;
import com.xw.project.mapper.DisStockInMapper;
import com.xw.project.service.disaster.DisStockInService;
import com.xw.project.vo.DisStockInVo;
import com.xw.project.vo.DisStockWaitInVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.beanutils.BeanUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yuli
 * @since 2020-04-13
 */
@Service
public class DisStockInServiceImpl extends ServiceImpl<DisStockInMapper, DisStockIn> implements DisStockInService {
    @Autowired
    private DisStockInMapper disStockInMapper;
    @Autowired
    private DisReserveNoticeMapper disReserveNoticeMapper;
    @Autowired
    private DisReserveNoticeDetailMapper disReserveNoticeDetailMapper;
    @Autowired
    private DisStockInDetailMapper disStockInDetailMapper;

    @Override
    public IPage<DisStockWaitInVo> getWaitInList(Integer current, Integer size) {
        Page<DisStockWaitInVo> pageInfo = new Page((long) current, (long) size);
        IPage<DisStockWaitInVo> disStockWaitInVos= disStockInMapper.getWaitInList(pageInfo);
        return disStockWaitInVos;
    }

    @Override
    public IPage<DisStockInVo> getInList(Integer current, Integer size) {
        Page<DisStockInVo> pageInfo = new Page((long) current, (long) size);
        IPage<DisStockInVo> disStockInVos= disStockInMapper.getInList(pageInfo);
        return disStockInVos;
    }

    @Override
    public int stockIn(Map<String, Object> map) {
        DisStockIn disStockIn = new DisStockIn();
        List<Map<String, Object>> materialDetails = (List<Map<String, Object>>) map.get("materialDetails");
        try {
            BeanUtils.populate(disStockIn, map);
            String id = UUIDGenerator.getUUID();
            disStockIn.setId(id);
            //保存物资详情
            if (null != materialDetails && materialDetails.size() > 0) {
                Iterator<Map<String, Object>> iterator = materialDetails.iterator();
                while (iterator.hasNext()) {
                    Map<String, Object> next = iterator.next();
                    DisStockInDetail disStockInDetail = new DisStockInDetail();
                    BeanUtils.populate(disStockInDetail, next);
                    disStockInDetail.setNoticeId(id);
                    disStockInDetail.setId(UUIDGenerator.getUUID());
                    disStockInDetailMapper.insert(disStockInDetail);
                    //更新入库数量
                    //本次入库数量
                    BigDecimal amount = disStockInDetail.getAmount();
                    //上次已入库数量
                    BigDecimal inAmount=new BigDecimal(String.valueOf(map.get("inAmount")));
                    DisReserveNoticeDetail disReserveNoticeDetail=new DisReserveNoticeDetail();
                    //更新已入库数量
                    disReserveNoticeDetail.setInAmount(inAmount.add(amount));
                    disReserveNoticeDetailMapper.insert(disReserveNoticeDetail);
                }
            }
            //更新入库状态
            DisReserveNotice disReserveNotice=new DisReserveNotice();
            disReserveNotice.setId(String.valueOf(map.get("noticeId")));
            disReserveNotice.setStatus(String.valueOf(map.get("status")));
            disReserveNoticeMapper.updateById(disReserveNotice);
            return disStockInMapper.insert(disStockIn);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


}
