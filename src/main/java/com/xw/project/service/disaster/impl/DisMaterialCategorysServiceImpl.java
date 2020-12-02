package com.xw.project.service.disaster.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.common.util.UUIDGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.entity.DisMaterialCategorys;
import com.xw.project.mapper.DisMaterialCategorysMapper;
import com.xw.project.service.disaster.DisMaterialCategorysService;
import com.xw.project.vo.DisMaterialCategorysVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>救灾救援-仓库管理-物资类别-服务实现类
 *
 * @author weiLiang
 * @since 2020-07-13
 */
@Service
public class DisMaterialCategorysServiceImpl extends ServiceImpl<DisMaterialCategorysMapper, DisMaterialCategorys> implements DisMaterialCategorysService {
    @Autowired
    private DisMaterialCategorysMapper disMaterialCategorysMapper;

    @Override
    public IPage<DisMaterialCategorysVo> pageListCategory(String category, String code) {
        IPage<DisMaterialCategorysVo> page = new Page<>();
        List<DisMaterialCategorysVo> disMaterialCategorys = disMaterialCategorysMapper.pageListCategory(category, code);
        HashSet<String> idSet = new HashSet<>();
        // 如果有查询语句，则需要对子类分类进行重新处理，将其父类也检索出来
        boolean isSearchNull = (category != null && !"".equals(category)) || (code != null && !"".equals(code));
        if (isSearchNull) {
            for (DisMaterialCategorysVo vo : disMaterialCategorys) {
                // 将查询到的 类别 先加进来，再处理其父类
                idSet.add(vo.getId());
                if (!"0".equals(vo.getId()) && !"0".equals(vo.getParentId())) {
                    String[] temp = vo.getParentIds().split(",");
                    Collections.addAll(idSet, temp);
                }
            }
            // 清空原来的记录，添加重新搜索的记录
            disMaterialCategorys.clear();
            if (!idSet.isEmpty()) {
                disMaterialCategorys.addAll(disMaterialCategorysMapper.getCategoryByIds(idSet));
            }
        }
        page.setTotal(disMaterialCategorys.size());
        page.setRecords(createTree("0", disMaterialCategorys));
        return page;
    }

    @Override
    public Integer addCategory(DisMaterialCategorys disMaterialCategorys) {
        String parentId = disMaterialCategorys.getParentId();
        String parentIds = "";
        if ("0".equals(parentId)) {
            parentIds = "0";
        } else {
            parentIds = disMaterialCategorysMapper.getParentIdsByParent(parentId) + "," + parentId;
        }
        disMaterialCategorys.setParentIds(parentIds);
        disMaterialCategorys.setId(UUIDGenerator.getUUID());
        return disMaterialCategorysMapper.addCategory(disMaterialCategorys);
    }

    @Override
    public Integer deleteMaterialCategoryById(String id) {
        return disMaterialCategorysMapper.deleteMaterialCategoryById(id);
    }

    @Override
    public Integer updateCategory(DisMaterialCategorys disMaterialCategorys) {
        return disMaterialCategorysMapper.updateCategory(disMaterialCategorys);
    }

    /**
     * 递归树，获取的物资类别，需要进行处理，将子类别挂载到父类别上
     *
     * @param pid                  根节点，默认会传 0
     * @param disMaterialCategorys 需要递归绑定的类别列表
     * @return List<DisMaterialCategorysVo> 返回一个前端需要的数据列表
     */
    private List<DisMaterialCategorysVo> createTree(String pid, List<DisMaterialCategorysVo> disMaterialCategorys) {
        List<DisMaterialCategorysVo> result = new ArrayList<>();
        for (DisMaterialCategorysVo disMaterialCategoryVo : disMaterialCategorys) {
            if (pid.equals(disMaterialCategoryVo.getParentId())) {
                result.add(disMaterialCategoryVo);
                List<DisMaterialCategorysVo> tree = createTree(disMaterialCategoryVo.getId(), disMaterialCategorys);
                if (tree.size() > 0) {
                    disMaterialCategoryVo.setChildren(tree);
                }

            }
        }
        return result;
    }

    @Override
    public IPage<DisMaterialCategorysVo> categoryList() {
        IPage<DisMaterialCategorysVo> result = new Page<>();
        List<DisMaterialCategorysVo> disMaterialCategorys = disMaterialCategorysMapper.selectCategoryList();
        result.setTotal(disMaterialCategorys.size());
        result.setRecords(createTree("0", disMaterialCategorys));
        return result;
    }
}
