package com.xw.project.service.disaster.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.common.util.UUIDGenerator;
import com.xw.project.entity.DisDonatedMaterials;
import com.xw.project.entity.DisDonatedMaterialsDetail;
import com.xw.project.mapper.DisDonatedMaterialsDetailMapper;
import com.xw.project.mapper.DisDonatedMaterialsMapper;
import com.xw.project.mapper.DisDonationIntentionMapper;
import com.xw.project.service.disaster.DisDonatedMaterialsService;
import com.xw.project.vo.DisDonatedMaterialsVo;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @author dy
 * @since 2020-04-08
 */
@Service
public class DisDonatedMaterialsServiceImpl extends ServiceImpl<DisDonatedMaterialsMapper, DisDonatedMaterials> implements DisDonatedMaterialsService {
    @Autowired
    private DisDonationIntentionMapper donationIntentionMapper;
    @Autowired
    private DisDonatedMaterialsMapper disDonatedMaterialsMapper;
    @Autowired
    private DisDonatedMaterialsDetailMapper disDonatedMaterialsDetailMapper;

    @Override
    public List<Map<String, Object>> intentionList() {
        //需要权限以及组织机构控制只有省红会有捐赠意向
        List<Map<String, Object>> donationIntentions = donationIntentionMapper.selectIntentionList();
        return donationIntentions;
    }

    @Override
    public int save(Map<String, Object> map) {
        DisDonatedMaterials disDonatedMaterials = new DisDonatedMaterials();
        List<Map<String, Object>> materialDetails = (List<Map<String, Object>>) map.get("materialDetails");
        try {
            BeanUtils.populate(disDonatedMaterials, map);
            String id = UUIDGenerator.getUUID();
            disDonatedMaterials.setId(id);
            String donationDate = (String) map.get("donationDate");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            disDonatedMaterials.setDonationDate(format.parse(donationDate));
            //保存物资详情
            if (null != materialDetails && materialDetails.size() > 0) {
                Iterator<Map<String, Object>> iterator = materialDetails.iterator();
                while (iterator.hasNext()) {
                    Map<String, Object> next = iterator.next();
                    DisDonatedMaterialsDetail disDonatedMaterialsDetail = new DisDonatedMaterialsDetail();
                    BeanUtils.populate(disDonatedMaterialsDetail, next);
                    disDonatedMaterialsDetail.setDonatedId(id);
                    disDonatedMaterialsDetail.setId(UUIDGenerator.getUUID());
                    disDonatedMaterialsDetailMapper.insert(disDonatedMaterialsDetail);
                }
            }
            int result = disDonatedMaterialsMapper.insert(disDonatedMaterials);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public IPage<DisDonatedMaterialsVo> pagesList(Integer current, Integer size) {
        Page<DisDonatedMaterialsVo> pageInfo = new Page((long) current, (long) size);
       return disDonatedMaterialsMapper.pagesList(pageInfo);
    }
}
