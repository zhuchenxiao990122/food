
package com.xw.project.mapper;

import com.xw.common.entity.User;
import com.xw.project.entity.TreeNode;
import com.xw.project.vo.OrganizeInternalVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface OrganizeManageMapper {

    List<TreeNode> selectAllTrees();

    List<OrganizeInternalVo> listOrgUsers(String orgId);
}
