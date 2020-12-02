package com.xw.project.service.disaster;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.project.entity.DisMaterialCategorys;
import com.xw.project.vo.DisMaterialCategorysVo;

/**
 * <p>救灾救援-仓库管理-物资种类管理-服务类
 *
 * @author yuli
 * @since 2020-04-09
 */
public interface DisMaterialCategorysService extends IService<DisMaterialCategorys> {

    /**
     * 根据参数列表查找物资类别
     *
     * @param category 类别名称
     * @param code 类别编码
     * @return IPage<DisMaterialCategorysVo> 返回类别信息，并封装在 page 里
     * */
    IPage<DisMaterialCategorysVo> pageListCategory(String category, String code);

    /**
     * 新增物资类别
     *
     * @param disMaterialCategorys 物资类别的实体类
     * @return Integer 返回数据库的结果，大于 0 表示新增成功
     * */
    Integer addCategory(DisMaterialCategorys disMaterialCategorys);

    /**
     * 删除物资类别
     *
     * @param id 物资类别的 Id
     * @return Integer 返回数据库的结果，大于 0 表示删除成功（逻辑删除，置 del_flag = 1）
     * */
    Integer deleteMaterialCategoryById(String id);

    /**
     * 更新物资类别信息
     *
     * @param disMaterialCategorys 物资类别的实体类
     * @return Integer 返回数据库的结果，大于 0 表示更新成功
     * */
    Integer updateCategory(DisMaterialCategorys disMaterialCategorys);

    /**
     * 物资分类信息
     *
     * @return Integer 返回数据库的结果，大于 0 表示更新成功
     * */
    IPage<DisMaterialCategorysVo> categoryList();
}
