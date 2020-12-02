package com.xw.project.dto;

import com.xw.project.util.PageHelper;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 救灾救援-队伍管理-查询-参数类
 * @author weiLiang
 * @since 2020-06-23
 */
@Data
public class DisRescueTeamDto extends PageHelper {
    private String orgId;
    private String teamType;
    private String teamName;
    private String[] establishDate;
}
