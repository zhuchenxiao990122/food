package com.xw.project.service.organize;

import com.xw.common.result.RestResponse;
import com.xw.project.entity.OrgHistory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.project.entity.OrgMember;
import com.xw.project.vo.OrgHistoryVo;
import com.xw.project.vo.OrgInternalUserVo;
import com.xw.project.vo.OrganizeInternalVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caoshuhao
 * @since 2020-06-22
 */
public interface OrgHistoryService extends IService<OrgHistory> {

    RestResponse<List<OrgHistory>> listOrgHistory(String parentId);

    RestResponse<List<OrgMember>> listOrganizeMember(String orgId);

    RestResponse<String> delete(String id);

    RestResponse<String> updateHistory(OrgHistoryVo orgHistoryVo);

    RestResponse<String> addOrgHistory(OrgHistoryVo orgHistoryVo);

    RestResponse<String> transition( OrgHistoryVo orgHistoryVo);
}
