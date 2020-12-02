package com.xw.project.dto;

import com.xw.project.util.PageHelper;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class DisRescueRequestDto extends PageHelper {
    private String isDel;
    private String publishCode;
    private String receiveOrgId;
}
