package com.xw.project.mapper;

import com.xw.project.entity.DisMaterialCategorys;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.DisMaterialCategorysVo;
import org.apache.ibatis.annotations.Param;

import java.util.HashSet;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yuli
 * @since 2020-04-09
 */
public interface DisMaterialCategorysMapper extends BaseMapper<DisMaterialCategorys> {

    /**
     * 根据参数列表查找物资类别
     *
     * @param category 类别名称
     * @param code     类别编码
     * @return IPage<DisMaterialCategorysVo> 返回类别信息，并封装在 page 里
     */
    List<DisMaterialCategorysVo> pageListCategory(@Param("category") String category, @Param("code") String code);

    /**
     * 根据重新整理的 id，查找出所有在 ids 中的类别
     *
     * @param ids 类别 id 集合
     * @return IPage<DisMaterialCategorysVo> 返回类别信息，并封装在 page 里
     */
    List<DisMaterialCategorysVo> getCategoryByIds(@Param("ids") HashSet<String> ids);

    /**
     * 根据类别 Id，返回其到根节点的所有父节点 parentIds
     *
     * @param parentId 传递过来的父类 Id
     * @return String 返回 parentIds：所有父类节点
     */
    String getParentIdsByParent(@Param("parentId") String parentId);

    /**
     * 新增物资类别
     *
     * @param disMaterialCategorys 物资类别的实体类
     * @return Integer 返回数据库的结果，大于 0 表示新增成功
     */
    Integer addCategory(@Param("category") DisMaterialCategorys disMaterialCategorys);

    /**
     * 删除物资类别
     *
     * @param id 物资类别的 Id
     * @return Integer 返回数据库的结果，大于 0 表示删除成功（逻辑删除，置 del_flag = 1）
     */
    Integer deleteMaterialCategoryById(String id);

    /**
     * 更新物资类别信息
     *
     * @param disMaterialCategorys 物资类别的实体类
     * @return Integer 返回数据库的结果，大于 0 表示更新成功
     */
    Integer updateCategory(@Param("category") DisMaterialCategorys disMaterialCategorys);

    /**
     * 查看所有物资类别信息
     *
     * @return Integer 返回数据库的结果，大于 0 表示更新成功
     */
    List<DisMaterialCategorysVo> selectCategoryList();
}
