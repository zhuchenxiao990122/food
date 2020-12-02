package com.xw.project.service.organize;

import com.xw.common.entity.User;
import com.xw.common.result.RestResponse;
import com.xw.project.entity.OrgRedCross;
import com.xw.project.entity.TreeNode;
import com.xw.project.vo.OrgInternalUserVo;
import com.xw.project.vo.OrganizeInternalVo;
import com.xw.system.vo.OrganizeVO;

import java.sql.SQLException;
import java.util.List;

public interface OrganizeManageService {
    List<TreeNode> listOrganize();

    OrgRedCross organizeDetailInfo(String treeId);

    String updateOrganizeBase(OrgRedCross orgRedCross);

    RestResponse<List<OrganizeInternalVo>> listOrganizationUnit(String parentId, String orgType);

    RestResponse<String> deleteInternalOrganize(String orgId);

    RestResponse<String> updateInternalOrganize(OrgInternalUserVo orgInternalUserVo);

    RestResponse<List<OrganizeInternalVo>> listOrgUser(String orgId);

    RestResponse<String> addInternalOrg(OrgInternalUserVo orgInternalUserVo);
}
