package com.xw.project.service.organize;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.project.dto.OrgPirmaryDto;
import com.xw.project.entity.OrgPrimary;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.project.vo.OrganizePrimaryVo;
import com.xw.system.entity.Organize;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 基层组织信息表  服务类
 * </p>
 *
 * @author dy
 * @since 2020-01-07
 */
public interface OrgPrimaryService extends IService<OrgPrimary> {

    IPage<OrganizePrimaryVo> organizePrimary(OrgPirmaryDto orgPirmaryDto);

    String addOrganizePrimary(OrganizePrimaryVo organizePrimaryVo);

    String updateOrganization(OrganizePrimaryVo organizePrimaryVo);

    String deleteOrganization(String orgId);

    String organzeGrasssCoding(String parentId, String dictId);

    List<Organize> orgparimaryName(String parentId);

}
