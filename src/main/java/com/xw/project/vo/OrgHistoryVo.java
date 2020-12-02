package com.xw.project.vo;

import com.xw.core.constant.DocConstant;
import com.xw.project.entity.OrgHistory;
import com.xw.project.entity.OrgMember;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@ApiModel
@Data
public class OrgHistoryVo {
    @ApiModelProperty("组织机构id")
    private String id;
    @ApiModelProperty("组织机构Id")
    private String orgId;
    @ApiModelProperty("所属组织Id")
    private String parentId;
    @NotEmpty(message = "机构名称不可为空")
    @ApiModelProperty("组织机构名称")
    private String fullName;
    @NotEmpty(message = "机构届数不可为空")
    @ApiModelProperty("届数")
    private String sessionNum;
    @ApiModelProperty("机构成员")
    private List<OrgMember> orgMemberList;

}
