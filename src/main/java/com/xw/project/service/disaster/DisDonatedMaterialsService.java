package com.xw.project.service.disaster;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.project.entity.DisDonatedMaterials;
import com.xw.project.vo.DisDonatedMaterialsVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dy
 * @since 2020-04-08
 */
public interface DisDonatedMaterialsService extends IService<DisDonatedMaterials> {

    List<Map<String,Object>> intentionList();

    int save(Map<String, Object> map);

    IPage<DisDonatedMaterialsVo> pagesList(Integer current, Integer size);
}
