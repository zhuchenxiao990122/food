package com.xw.project.dto;

import com.xw.project.util.PageHelper;
import lombok.Data;

/**
 * 救灾救援-人员管理-查询-参数类
 * @author weiLiang
 * @since 2020-06-23
 */
@Data
public class DisRescueMemberDto extends PageHelper {
    private String orgId;
    private String teamType;
    private String teamName;
    private String memberName;
    private String[] joinDate;
}
