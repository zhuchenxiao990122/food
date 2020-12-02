package com.xw.project.dto;

import com.xw.project.entity.DisRescueMember;
import lombok.Data;

import java.util.List;

@Data
public class DisRescueMemberEntityDto {
    private String id;
    private String orgId;
    private String teamType;
    private String teamId;
    private List<DisRescueMember> detailData;
}
