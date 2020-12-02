package com.xw.project.service.disaster;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.project.dto.DisMaterialWarehouseDto;
import com.xw.project.entity.DisMaterialWarehouse;
import com.xw.project.vo.DisMaterialWarehouseVo;

/**
 * <p>救灾救援-仓库管理-仓库信息管理-服务类
 *
 * @author yuli
 * @since 2020-04-10
 */
public interface DisMaterialWarehouseService extends IService<DisMaterialWarehouse> {
    /**
     * 根据参数列表查找仓库信息
     *
     * @param disMaterialWarehouseDto 封装了查询条件
     * @return IPage<DisMaterialWarehouseVo> 返回仓库信息，并封装在page里
     * */
    IPage<DisMaterialWarehouseVo> pageListWarehouseInfo(DisMaterialWarehouseDto disMaterialWarehouseDto);

    /**
     * 根据组织 Id 查找区域编号
     *
     * @param orgId 组织 Id
     * @return String 返回获取的区域编号
     * */
    String getAreaId(String orgId);

    /**
     * 新增仓库信息
     *
     * @param disMaterialWarehouse 仓库信息的实体类
     * @return Integer 返回数据库的结果，大于 0 表示新增成功
     * */
    Integer addWarehouseInfo(DisMaterialWarehouse disMaterialWarehouse);

    /**
     * 删除仓库信息
     *
     * @param id 仓库的 Id
     * @return Integer 返回数据库的结果，大于 0 表示删除成功（逻辑删除，置 del_flag = 1）
     * */
    Integer deleteWarehouseInfoById(String id);

    /**
     * 更新仓库信息
     *
     * @param disMaterialWarehouse 仓库信息的实体类
     * @return Integer 返回数据库的结果，大于 0 表示更新成功
     * */
    Integer updateWareHouseInfo(DisMaterialWarehouse disMaterialWarehouse);

}
